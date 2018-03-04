package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;

import edu.ilstu.biology.flytranscriptionwebapp.model.CorrelationResult;
import edu.ilstu.biology.flytranscriptionwebapp.model.FinalResponseCorrelationResult;
import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneCorrelatedResult;
import edu.ilstu.biology.flytranscriptionwebapp.validation.GeneValidator;

/**
 * This class is responsible for retrieving a list of highly correlated genes
 * based on the input data using Pearson's Correlation
 * 
 * @author codingbash
 *
 */
@Component
public class GenomicCorrelationAnalysis {

	@Autowired
	@Qualifier("genomeData")
	private List<Gene> genomeData;

	@Autowired
	private GeneValidator geneValidator;

	/**
	 * Gaussian CDF z-values for different confidence levels 100(1-a)%
	 * 
	 * 95% confidence level used by default
	 * 
	 */
	private static final double CI_ZVAL_90 = 1.645;
	private static final double CI_ZVAL_95 = 1.96;
	private static final double CI_ZVAL_99 = 2.58;

	/**
	 * Amount of significant figures used.
	 * 
	 * 3 SF used by default
	 */
	private static final int SIGNIFICANT_FIGURES = 3;

	/**
	 * Handler method: retrieves and validates gene, and retrieves the
	 * correlation result
	 */
	// TODO: Bad method design with custom gene, overload instead
	public FinalResponseCorrelationResult retrieveMrnaCorrelationResults(Gene customGene, String inputIdentifier,
			List<Integer> selectedExpressionIndices, List<String> inputGeneOfInterestList, Integer geneResultCount) {

		// TODO: I like this ternary, maybe not overloading is fine, take more
		// thought on this
		Gene inputGene = (customGene == null) ? findGeneInGenome(inputIdentifier) : customGene;

		geneValidator.validateFoundGene(inputGene); // throws unchecked
													// InvalidGeneException

		FinalResponseCorrelationResult finalResult = computeCorrelationResult(inputGene, selectedExpressionIndices,
				inputGeneOfInterestList, geneResultCount);

		return finalResult;
	}

	/**
	 * Main logic to compute the correlation results for all genes in genome.
	 * 
	 * @param foundGene
	 * @param selectedExpressionIndices
	 * @param inputGeneOfInterestList
	 * @param geneResultCount
	 * @return
	 */
	private FinalResponseCorrelationResult computeCorrelationResult(Gene foundGene,
			List<Integer> selectedExpressionIndices, List<String> inputGeneOfInterestList, Integer geneResultCount) {

		SortedSet<GeneCorrelatedResult> sortedSet = new TreeSet<GeneCorrelatedResult>();

		List<GeneCorrelatedResult> resultGeneOfInterestList = new ArrayList<GeneCorrelatedResult>(1);
		Gene finalFoundGene = null;
		double[] foundGeneRna = selectGeneRnaIndices(foundGene, selectedExpressionIndices);
		/*
		 * Cloning the iterated gene to prevent manipulating genome data. This
		 * will be the gene added to queue
		 */
		finalFoundGene = new Gene(foundGene);
		finalFoundGene.setRnaExpData(Ints.toArray(Doubles.asList(foundGeneRna)));

		/*
		 * Calculate correlation
		 */
		for (Gene iteratedGene : genomeData) {
			if (iteratedGene.getRnaExpData() != null
					&& geneValidator.compareGeneRnaDataLength(foundGene, iteratedGene)) {
				double[] iteratedGeneRna = selectGeneRnaIndices(iteratedGene, selectedExpressionIndices);
				RealMatrix matrix = retrieveRnaDataMatrix(foundGeneRna, iteratedGeneRna);
				CorrelationResult corrResult = conductPearsonsCorrelation(matrix);
				if (corrResult != null) {
					GeneCorrelatedResult resultGene = createResultGene(iteratedGene, iteratedGeneRna, corrResult);
					sortedSet.add(resultGene);
					geneOfInterestCheck(inputGeneOfInterestList, resultGeneOfInterestList, resultGene);
				}
			}
		}

		/*
		 * Set geneOfInterestList Ranks
		 */
		List<GeneCorrelatedResult> correlatedResultList = new ArrayList<GeneCorrelatedResult>(sortedSet);
		for (GeneCorrelatedResult resultGeneOfInterest : resultGeneOfInterestList) {
			resultGeneOfInterest.setRank(Collections.binarySearch(correlatedResultList, resultGeneOfInterest,
					new Comparator<GeneCorrelatedResult>() {
						public int compare(GeneCorrelatedResult u1, GeneCorrelatedResult u2) {
							return u1.compareTo(u2);
						}
					}));
		}

		/*
		 * Set correlatedResult ranks for top {geneResultCount}
		 */
		correlatedResultList = correlatedResultList.subList(0, Math.min(correlatedResultList.size(), geneResultCount));
		for (int listIndex = 0; listIndex < correlatedResultList.size(); listIndex++) {
			correlatedResultList.get(listIndex).setRank(listIndex + 1);
		}

		/*
		 * Prepare final result
		 */
		FinalResponseCorrelationResult result = new FinalResponseCorrelationResult();
		result.setInputGene(finalFoundGene);
		result.setCorrelationResults(correlatedResultList);
		result.setCorrelationResultsForGenesOfInterest(resultGeneOfInterestList);
		return result;
	}

	/**
	 * Checks if iterated gene is in target geneOfInterestList, if it is, add to
	 * result list and remove target from target geneOfInterestList
	 */
	private void geneOfInterestCheck(List<String> inputGeneOfInterestList,
			List<GeneCorrelatedResult> resultGeneOfInterestList, GeneCorrelatedResult resultGene) {
		final ListIterator<String> li = inputGeneOfInterestList.listIterator();
		while (li.hasNext()) {
			Gene potentialGeneOfInterest = determineGeneIdentifierMatch(resultGene.getGene(), li.next());

			if (potentialGeneOfInterest != null) {
				resultGeneOfInterestList.add(resultGene);
				li.remove();
				break;
			}
		}
	}

	/**
	 * Create the result gene object from the gene and correlation result
	 * 
	 * @param iteratedGene
	 * @param iteratedGeneRna
	 * @param corrResult
	 * @return
	 */
	private GeneCorrelatedResult createResultGene(Gene iteratedGene, double[] iteratedGeneRna,
			CorrelationResult corrResult) {
		GeneCorrelatedResult resultGene = new GeneCorrelatedResult();
		/*
		 * Cloning the iterated gene to prevent manipulating genome data. This
		 * will be the gene added to queue
		 */
		Gene iteratedGeneClone = new Gene(iteratedGene);
		iteratedGeneClone.setRnaExpData(Ints.toArray(Doubles.asList(iteratedGeneRna)));
		resultGene.setGene(iteratedGeneClone);
		resultGene.setCorrResult(corrResult);
		return resultGene;
	}

	/**
	 * Given a matrix, calculate the Pearson's correlation coefficient
	 * 
	 * @param matrix
	 * @return
	 */
	private CorrelationResult conductPearsonsCorrelation(RealMatrix matrix) {
		PearsonsCorrelation correlator = new PearsonsCorrelation(matrix);
		double rVal = correlator.getCorrelationMatrix().getColumn(0)[1];
		double seVal = correlator.getCorrelationStandardErrors().getColumn(0)[1];
		double ciVal = CI_ZVAL_95 * seVal;
		double pVal = correlator.getCorrelationPValues().getColumn(0)[1];

		if (!Double.isNaN(rVal)) {
			CorrelationResult corrResult = new CorrelationResult(rVal, seVal, ciVal, CI_ZVAL_95, pVal,
					new MathContext(SIGNIFICANT_FIGURES));
			return corrResult;
		} else {
			return null;
		}

	}

	/**
	 * Filter out the RNA data with only the indices given by the user
	 * 
	 * @param targetGene
	 * @param selectedExpressionIndices
	 * @return
	 */
	private double[] selectGeneRnaIndices(Gene targetGene, List<Integer> selectedExpressionIndices) {
		double[] preTargetGeneRna = Doubles.toArray(Ints.asList(targetGene.getRnaExpData()));
		double[] targetGeneRna = new double[selectedExpressionIndices.size()];

		for (int listIndex = 0; listIndex < selectedExpressionIndices.size(); listIndex++) {
			targetGeneRna[listIndex] = preTargetGeneRna[selectedExpressionIndices.get(listIndex)];
		}

		return targetGeneRna;

	}

	/**
	 * Create a matrix from two RNA data lists
	 * 
	 * @param firstGeneRna
	 * @param secondGeneRna
	 * @return
	 */
	private RealMatrix retrieveRnaDataMatrix(double[] firstGeneRna, double[] secondGeneRna) {

		double[][] data = { firstGeneRna, secondGeneRna };

		return MatrixUtils.createRealMatrix(data).transpose();
	}

	/**
	 * Find the gene in genome with the inputIdentifier based on rules
	 * 
	 * @param inputIdentifier
	 * @return
	 */
	private Gene findGeneInGenome(String inputIdentifier) {
		Gene foundGene = null;
		for (Gene gene : genomeData) {
			foundGene = determineGeneIdentifierMatch(gene, inputIdentifier);
			if (foundGene != null) {
				return foundGene;
			}
		}
		return null;
	}

	/**
	 * Determine if a gene matches the given input identifier from the user
	 * 
	 * @param gene
	 * @param inputIdentifier
	 * @return
	 */
	private Gene determineGeneIdentifierMatch(Gene gene, String inputIdentifier) {
		/*
		 * TODO: This is a really ugly way of checking if the gene is found -
		 * refactor
		 */
		if (StringUtils.equalsIgnoreCase(gene.getDbIdentifier(), StringUtils.trim(inputIdentifier))) {
			/*
			 * We found the gene - let's get its data
			 */
			return gene;
		} else if (StringUtils.equalsIgnoreCase(gene.getSecondaryIdentifier(), StringUtils.trim(inputIdentifier))) {
			/*
			 * We found the gene - let's get its data
			 */
			return gene;
		} else if (StringUtils.equalsIgnoreCase(gene.getGeneName(), StringUtils.trim(inputIdentifier))) {
			/*
			 * We found the gene - let's get its data
			 */
			return gene;
		} else {
			// TODO: Since this is not an ID, this should be added to a
			// "potential" list, and user should decide which gene to select
			for (String synonym : gene.getSynonyms()) {
				if (StringUtils.equalsIgnoreCase(synonym, StringUtils.trim(inputIdentifier))) {
					return gene;
				}
			}
		}
		return null;
	}
}
