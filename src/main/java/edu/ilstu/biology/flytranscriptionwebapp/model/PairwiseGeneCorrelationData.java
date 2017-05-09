package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

public class PairwiseGeneCorrelationData {
	private List<Integer> inputGeneData;
	private List<Integer> targetGeneData;

	public List<Integer> getInputGeneData() {
		return inputGeneData;
	}

	public void setInputGeneData(List<Integer> inputGeneData) {
		this.inputGeneData = inputGeneData;
	}

	public List<Integer> getTargetGeneData() {
		return targetGeneData;
	}

	public void setTargetGeneData(List<Integer> targetGeneData) {
		this.targetGeneData = targetGeneData;
	}

}
