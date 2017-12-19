package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.primitives.Ints;

import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;

@Component
public class RetrieveCorrelationData {

	@Autowired
	@Qualifier("genomeData")
	private List<Gene> genomeData;

	// TODO: Remove duplicate code, add modularity
	public List<Integer> retrieveCorrelationData(Gene customGene, List<Integer> selectedIndices) {
		List<Integer> correlationData = Ints.asList(customGene.getRnaExpData());

		/*
		 * Now only keep indices that were selected
		 */
		List<Integer> finalCorrelationData = new ArrayList<Integer>(selectedIndices.size());
		for(Integer index : selectedIndices){
			finalCorrelationData.add(correlationData.get(index));
		}
		
		// Find out the better way of creating empty lists
		return finalCorrelationData;
	}
	
	public List<Integer> retrieveCorrelationData(String inputIdentifier, List<Integer> selectedIndices) {
		List<Integer> correlationData = null;
		/*
		 * Step 1: Linear search for gene
		 */
		for (Gene gene : genomeData) {
			/*
			 * TODO: This is a really ugly way of checking if the gene is found
			 * - refactor
			 */
			if (StringUtils.equals(gene.getDbIdentifier(), StringUtils.trim(inputIdentifier))) {
				/*
				 * We found the gene - let's get its data
				 */
				correlationData = Ints.asList(gene.getRnaExpData());
				break;
			} else if (StringUtils.equals(gene.getSecondaryIdentifier(), StringUtils.trim(inputIdentifier))) {
				/*
				 * We found the gene - let's get its data
				 */
				correlationData = Ints.asList(gene.getRnaExpData());
				break;
			} else if (StringUtils.equals(gene.getGeneName(), StringUtils.trim(inputIdentifier))) {
				/*
				 * We found the gene - let's get its data
				 */
				correlationData = Ints.asList(gene.getRnaExpData());
				break;
			}
		}

		/*
		 * Now only keep indices that were selected
		 */
		List<Integer> finalCorrelationData = new ArrayList<Integer>(selectedIndices.size());
		for(Integer index : selectedIndices){
			finalCorrelationData.add(correlationData.get(index));
		}
		
		// Find out the better way of creating empty lists
		return finalCorrelationData;
	}
}
