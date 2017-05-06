package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;

import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneCorrelatedResult;

@Component
public class GenomicCorrelationAnalysis {

	@Autowired
	@Qualifier("genomeData")
	private List<Gene> genomeData;

	@Autowired
	private PearsonsCorrelation pearsonsCorrelation;

	@SuppressWarnings("unchecked")
	public List<GeneCorrelatedResult> retrieveMrnaCorrelationResults(String geneName) {
		Gene foundGene = null;
		/*
		 * Step 1: Linear search for gene
		 */
		for (Gene gene : genomeData) {
			if (StringUtils.equals(gene.getGeneName(), StringUtils.trim(geneName))) {
				/*
				 * We found the gene - let's get its data
				 */
				foundGene = gene;
				break;
			}
		}

		/*
		 * If we found the gene, let's continue the process flow...
		 */
		if (foundGene != null) {
			/* TODO: Maybe this will get the top 100 results as expected?
			 * No this does not - in the future, change to a SortedSet like in this post: http://stackoverflow.com/questions/1846225/java-priorityqueue-with-fixed-size
			 * For now, just get a sublist
			 */
			Queue<GeneCorrelatedResult> queue = new PriorityQueue<GeneCorrelatedResult>(100);

			/*
			 * Now we calculate the pcorr for all genes! Let's
			 */
			for (Gene gene : genomeData) {
				// TODO: This may be bad for performance
				if (gene.getGeneName() != foundGene.getGeneName()) {
					double[] foundGeneRna = Doubles.toArray(Ints.asList(foundGene.getRnaExpData()));
					double[] targetGeneRna = Doubles.toArray(Ints.asList(gene.getRnaExpData()));
					double rVal = pearsonsCorrelation.correlation(foundGeneRna, targetGeneRna);
					// TODO: Not sure if this is the correct way to get the
					// pvals;
					double pVal = 1.0;// pearsonsCorrelation.getCorrelationPValues().getEntry(foundGeneRna.length,
										// targetGeneRna.length);
					if (!Double.isNaN(rVal)) {
						GeneCorrelatedResult resultGene = new GeneCorrelatedResult();
						resultGene.setGene(gene);
						resultGene.setrVal(rVal);
						resultGene.setpVal(pVal);
						queue.add(resultGene);
					}
				}
			}

			return new LinkedList<GeneCorrelatedResult>(queue).subList(0, 100);
		}
		/*
		 * The user entered a false gene, return null
		 */
		else {
			return null;
		}
	}
}
