package edu.ilstu.biology.flytranscriptionwebapp.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import edu.ilstu.biology.flytranscriptionwebapp.constants.ErrorConstants;
import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.model.Error;

@Component
public class GeneValidator {

	public void validateFoundGene(Gene foundGene) throws InvalidGeneException {
		if (foundGene == null) {
			Error error = new Error(ErrorConstants.UNFOUND_GENE_CODE, ErrorConstants.UNFOUND_GENE_MESSAGE);
			throw new InvalidGeneException(error, error.getMessage());

			/*
			 * TODO: RNA Expression data may need to be more than 2 for
			 * correlation to work?
			 */
		} else if (foundGene.getRnaExpData().length == 0) {
			Error error = new Error(ErrorConstants.GENE_WITH_NO_RNA_CODE,
					StringUtils.replace(ErrorConstants.GENE_WITH_NO_RNA_MESSAGE, "{}", foundGene.getDbIdentifier()));
			throw new InvalidGeneException(error, error.getMessage());
		}
	}

	/**
	 * Ensure that the dimensions of the RNA data in both genes are equal
	 * 
	 * @param firstGene
	 * @param secondGene
	 * @return
	 */
	public boolean compareGeneRnaDataLength(Gene firstGene, Gene secondGene) {
		return firstGene.getRnaExpData().length == secondGene.getRnaExpData().length;
	}
}
