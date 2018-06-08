package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.Map;

public class PairwiseCorrelationDataAjaxRequestBody {
	private String inputGene; // ID
	private String targetGene; // ID
	private String inputGeneName;
	private String targetGeneName;
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

	public String getInputGeneName() {
		return inputGeneName;
	}

	public void setInputGeneName(String inputGeneName) {
		this.inputGeneName = inputGeneName;
	}

	public String getTargetGeneName() {
		return targetGeneName;
	}

	public void setTargetGeneName(String targetGeneName) {
		this.targetGeneName = targetGeneName;
	}

	public Map<String, Boolean> getSelectedExpressionStages() {
		return selectedExpressionStages;
	}

	public void setSelectedExpressionStages(Map<String, Boolean> selectedExpressionStages) {
		this.selectedExpressionStages = selectedExpressionStages;
	}

}
