package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.math.MathContext;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

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

@Component
public class GenomicCorrelationAnalysis {

	@Autowired
	@Qualifier("genomeData")
	private List<Gene> genomeData;

	@Autowired
	private PearsonsCorrelation pearsonsCorrelation;

	private static final int CORRELATED_GENE_SIZE = 100;

	private static final double CI_ZVAL_90 = 1.645;
	private static final double CI_ZVAL_95 = 1.96;
	private static final double CI_ZVAL_99 = 2.58;

	// TODO: Generate this dynamically depending on input
	private static final int SIGNIFICANT_FIGURES = 3;

	/*
	 * TODO: Seperate the gene searching and the correlation retrieval to
	 * separate methods. Creat the Final... object in the controller
	 */
	public FinalResponseCorrelationResult retrieveMrnaCorrelationResults(String inputIdentifier,
			List<Integer> selectedExpressionIndices) {
		/*
		 * 
		 * TODO: Put this logic in another method. It seems to not belong here
		 */

		Gene foundGene = null;
		/*
		 * Step 1: Linear search for gene
		 */
		outerloop: for (Gene gene : genomeData) {
			/*
			 * TODO: This is a really ugly way of checking if the gene is found
			 * - refactor
			 */
			if (StringUtils.equalsIgnoreCase(gene.getDbIdentifier(), StringUtils.trim(inputIdentifier))) {
				/*
				 * We found the gene - let's get its data
				 */
				foundGene = gene;
				break;
			} else if (StringUtils.equalsIgnoreCase(gene.getSecondaryIdentifier(), StringUtils.trim(inputIdentifier))) {
				/*
				 * We found the gene - let's get its data
				 */
				foundGene = gene;
				break;
			} else if (StringUtils.equalsIgnoreCase(gene.getGeneName(), StringUtils.trim(inputIdentifier))) {
				/*
				 * We found the gene - let's get its data
				 */
				foundGene = gene;
				break;
			} else {
				// TODO: Since this is not an ID, this should be added to a
				// "potential" list, and user should decide which gene to select
				for (String synonym : gene.getSynonyms()) {
					if (StringUtils.equalsIgnoreCase(synonym, StringUtils.trim(inputIdentifier))) {
						foundGene = gene;
						break outerloop;
					}
				}
			}
		}

		/*
		 * If we found the gene, let's continue the process flow...
		 */
		if (foundGene != null && foundGene.getRnaExpData().length > 0) {
			/*
			 * TODO: Maybe this will get the top 100 results as expected? No
			 * this does not - in the future, change to a SortedSet like in this
			 * post:
			 * http://stackoverflow.com/questions/1846225/java-priorityqueue-
			 * with-fixed-size For now, just get a sublist
			 */
			Queue<GeneCorrelatedResult> queue = new PriorityQueue<GeneCorrelatedResult>(CORRELATED_GENE_SIZE);

			/*
			 * Now we calculate the pcorr for all genes! Let's
			 */
			for (Gene gene : genomeData) {
				// Ensure that the dimensions are equal
				if (gene.getRnaExpData() != null && gene.getRnaExpData().length == foundGene.getRnaExpData().length) {

					// TODO: This may be bad for performance
					// if (gene.getGeneName() != foundGene.getGeneName()) {
					/*
					 * Prepare the data
					 */
					double[] preFoundGeneRna = Doubles.toArray(Ints.asList(foundGene.getRnaExpData()));
					double[] preTargetGeneRna = Doubles.toArray(Ints.asList(gene.getRnaExpData()));
					
					
					double[] foundGeneRna = new double[selectedExpressionIndices.size()]; 
					double[] targetGeneRna = new double[selectedExpressionIndices.size()]; 
					
					/*
					 * Fill array with values that were selected
					 */
					int counter = 0;
					for(Integer index : selectedExpressionIndices){
						foundGeneRna[counter] = preFoundGeneRna[index];
						targetGeneRna[counter] = preTargetGeneRna[index];
						counter++;
					}
					/*
					 * Expression Stage Selection TODO: This method is starting
					 * to be less cohesive. Need to refactor
					 */

					double[][] data = { foundGeneRna, targetGeneRna };
					RealMatrix matrix = MatrixUtils.createRealMatrix(data).transpose();

					/*
					 * Conduct Pearson's correlation analysis
					 */
					PearsonsCorrelation correlator = new PearsonsCorrelation(matrix);
					double rVal = correlator.getCorrelationMatrix().getColumn(0)[1];
					// double zVal = 0.5 * Math.log((1 + rVal)/(1 - rVal));
					double seVal = correlator.getCorrelationStandardErrors().getColumn(0)[1];
					double ciVal = CI_ZVAL_95 * seVal;
					double pVal = correlator.getCorrelationPValues().getColumn(0)[1];

					if (!Double.isNaN(rVal)) {
						GeneCorrelatedResult resultGene = new GeneCorrelatedResult();
						CorrelationResult corrResult = new CorrelationResult(rVal, seVal, ciVal, CI_ZVAL_95, pVal,
								new MathContext(SIGNIFICANT_FIGURES));

						gene.setRnaExpData(Ints.toArray(Doubles.asList(targetGeneRna)));
						resultGene.setGene(gene);
						resultGene.setCorrResult(corrResult);

						queue.add(resultGene);
					}
				}
				// }
			}

			FinalResponseCorrelationResult result = new FinalResponseCorrelationResult();
			result.setInputGene(foundGene);
			result.setCorrelationResults(new LinkedList<GeneCorrelatedResult>(queue).subList(0, CORRELATED_GENE_SIZE));
			return result;
		}
		/*
		 * The user entered a false gene, return null
		 */
		else {
			return null;
		}
	}
}
