package edu.ilstu.biology.flytranscriptionwebapp.model;

public class PairwiseCorrelationDataAjaxRequestBody {
	private String inputGene;
	private String targetGene;

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

}
