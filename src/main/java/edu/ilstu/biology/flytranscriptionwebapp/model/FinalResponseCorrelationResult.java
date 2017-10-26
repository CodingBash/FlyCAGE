package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

public class FinalResponseCorrelationResult {
	/*
	 * This is fine to be Gene since it will most likely be needed on the
	 * front-end several times
	 */
	private Gene inputGene;

	/*
	 * TODO: Change from Gene object to String in GeneCorrelatedResult to save
	 * data
	 */
	private List<GeneCorrelatedResult> correlationResults;
	
	private List<GeneCorrelatedResult> correlationResultsForGenesOfInterest;

	public Gene getInputGene() {
		return inputGene;
	}

	public void setInputGene(Gene inputGene) {
		this.inputGene = inputGene;
	}

	public List<GeneCorrelatedResult> getCorrelationResults() {
		return correlationResults;
	}

	public void setCorrelationResults(List<GeneCorrelatedResult> correlationResults) {
		this.correlationResults = correlationResults;
	}

	public List<GeneCorrelatedResult> getCorrelationResultsForGenesOfInterest() {
		return correlationResultsForGenesOfInterest;
	}

	public void setCorrelationResultsForGenesOfInterest(List<GeneCorrelatedResult> correlationResultsForGenesOfInterest) {
		this.correlationResultsForGenesOfInterest = correlationResultsForGenesOfInterest;
	}
	
}
