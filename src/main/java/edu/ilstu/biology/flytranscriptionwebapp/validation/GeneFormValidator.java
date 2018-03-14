package edu.ilstu.biology.flytranscriptionwebapp.validation;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import edu.ilstu.biology.flytranscriptionwebapp.constants.ErrorConstants;
import edu.ilstu.biology.flytranscriptionwebapp.model.Error;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneForm;

@Component
public class GeneFormValidator {

	public void validateGeneForm(GeneForm geneForm) throws InvalidGeneFormException {
		validateGeneSpecies(geneForm.getGeneSpecies());
		validateInputIdentifier(geneForm.getInputIdentifier());
		validateGenesOfInterest(geneForm.getGenesOfInterest());
		validateGeneResultCount(geneForm.getGeneResultCount());
		validateQueryComment(geneForm.getQueryComment());
		validateExpressionStages(geneForm.getExpressionStages());
		validateCustomExpressionUsed(geneForm.getCustomExpressionUsed());
		validateCustomExpression(geneForm.getCustomExpression(), geneForm.getExpressionStages(),
				geneForm.getCustomExpressionUsed());
	}

	private void validateGeneSpecies(String geneSpecies) throws InvalidGeneFormException {
		// TODO: Also check if species is not supported once the dynamic logic
		// is complete
		if (StringUtils.isBlank(geneSpecies)) {
			Error error = new Error(ErrorConstants.INVALID_SPECIES_CODE,
					StringUtils.replace(ErrorConstants.INVALID_SPECIES_MESSAGE, "{}", geneSpecies));
			throw new InvalidGeneFormException(error, error.getMessage());
		}

	}

	private void validateInputIdentifier(String inputIdentifier) throws InvalidGeneFormException {
		// if (StringUtils.isBlank(inputIdentifier)) {
		// Error error = new Error(ErrorConstants.INVALID_IDENTIFIER_CODE,
		// StringUtils.replace(ErrorConstants.INVALID_IDENTIFIER_MESSAGE, "{}",
		// inputIdentifier));
		// throw new InvalidGeneFormException(error, error.getMessage());
		// }
		// TODO: Identifier not required as of now - but later should be once
		// the custom expression comes with an indicator
	}

	private void validateGenesOfInterest(String geneOfInterest) {
		// No validation needed
	}

	private void validateGeneResultCount(Integer geneResultCount) throws InvalidGeneFormException {
		if (geneResultCount == null) {
			Error error = new Error(ErrorConstants.INVALID_RESULT_COUNT_CODE,
					ErrorConstants.INVALID_RESULT_COUNT_NULL_MESSAGE);
			throw new InvalidGeneFormException(error, error.getMessage());
		} else {
			if (geneResultCount < 1) {
				Error error = new Error(ErrorConstants.INVALID_RESULT_COUNT_CODE, StringUtils
						.replace(ErrorConstants.INVALID_RESULT_COUNT_MIN_MESSAGE, "{}", geneResultCount.toString()));
				throw new InvalidGeneFormException(error, error.getMessage());

			} else if (geneResultCount > 25000) {
				Error error = new Error(ErrorConstants.INVALID_RESULT_COUNT_CODE, StringUtils
						.replace(ErrorConstants.INVALID_RESULT_COUNT_MAX_MESSAGE, "{}", geneResultCount.toString()));
				throw new InvalidGeneFormException(error, error.getMessage());
			}
		}
	}

	private void validateQueryComment(String queryComment) {
		// No validation needed
	}

	private void validateExpressionStages(Map<String, Boolean> expressionStages) throws InvalidGeneFormException {
		if (expressionStages == null) {
			Error error = new Error(ErrorConstants.INVALID_STAGEMAP_CODE, ErrorConstants.INVALID_STAGEMAP_NULL_MESSAGE);
			throw new InvalidGeneFormException(error, error.getMessage());
		} else {
			int count = 0;
			for (Boolean value : expressionStages.values()) {
				if (value != null && value.equals(Boolean.TRUE)) {
					count++;
				}
			}
			if (count < 3) {
				Error error = new Error(ErrorConstants.INVALID_STAGEMAP_CODE, StringUtils
						.replace(ErrorConstants.INVALID_STAGEMAP_MIN_MESSAGE, "{}", Integer.toString(count)));
				throw new InvalidGeneFormException(error, error.getMessage());

			}
		}
	}

	private void validateCustomExpressionUsed(String customExpressionUsed) throws InvalidGeneFormException {
		if (StringUtils.isBlank(customExpressionUsed)) {
			Error error = new Error(ErrorConstants.INVALID_CUSTOM_EXPRESSION_INDICATOR_CODE,
					ErrorConstants.INVALID_CUSTOM_EXPRESSION_INDICATOR_NULL_MESSAGE);
			throw new InvalidGeneFormException(error, error.getMessage());
		} else if (!customExpressionUsed.equals("true") && !customExpressionUsed.equals("false")) {
			Error error = new Error(ErrorConstants.INVALID_CUSTOM_EXPRESSION_INDICATOR_CODE, StringUtils.replace(
					ErrorConstants.INVALID_CUSTOM_EXPRESSION_INDICATOR_VALUE_MESSAGE, "{}", customExpressionUsed));
			throw new InvalidGeneFormException(error, error.getMessage());
		}
	}

	private void validateCustomExpression(Map<String, Integer> customExpression, Map<String, Boolean> expressionStages,
			String customExpressionUsed) throws InvalidGeneFormException {
		if (customExpression == null || expressionStages == null
				|| Boolean.parseBoolean(customExpressionUsed) == false) {
			// Null customExpression allowed since its optional
		} else {
			Integer firstValuePicked = null;
			boolean isVariation = false;
			for (Map.Entry<String, Integer> entry : customExpression.entrySet()) {
				Integer value = entry.getValue();
				String key = entry.getKey();
				Boolean isSelected = expressionStages.get(key);
				isSelected = (isSelected == null) ? false : isSelected;

				if (value != null && isSelected.equals(Boolean.TRUE)) {
					if (firstValuePicked == null) {
						firstValuePicked = value;
					} else {
						if (!firstValuePicked.equals(value)) {
							isVariation = true;
							break;
						}
					}
				}
			}

			if (isVariation == false && firstValuePicked != null) {
				Error error = new Error(ErrorConstants.INVALID_CUSTOM_EXPRESSION_CODE, StringUtils
						.replace(ErrorConstants.INVALID_CUSTOM_EXPRESSION_MESSAGE, "{}", firstValuePicked.toString()));
				throw new InvalidGeneFormException(error, error.getMessage());
			}
		}
	}
}
