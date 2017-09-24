package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.Map;

public class PairwiseCorrelationDataAjaxRequestBody {
	private String inputGene;
	private String targetGene;
	private Map<String, Boolean> selectedExpressionStages;

	public String getInputGene() {
		return inputGene;
	}

	public void setInputGene(String inputGene) {
		this.inputGene = inputGene;
	}

	public String getTargetGene() {
		return targetGene;
	}

	public void setTargetGene(String targetGene) {
		this.targetGene = targetGene;
	}

	public Map<String, Boolean> getSelectedExpressionStages() {
		return selectedExpressionStages;
	}

	public void setSelectedExpressionStages(Map<String, Boolean> selectedExpressionStages) {
		this.selectedExpressionStages = selectedExpressionStages;
	}

}
