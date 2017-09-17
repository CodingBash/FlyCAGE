package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;
import java.util.Map;

public class GeneForm {

	private String geneSpecies;

	private String inputIdentifier;

	private List<String> correlationOptions;

	private String queryComment;
	
	private Map<String, Boolean> expressionStages;

	public String getGeneSpecies() {
		return geneSpecies;
	}

	public void setGeneSpecies(String geneSpecies) {
		this.geneSpecies = geneSpecies;
	}

	public String getInputIdentifier() {
		return inputIdentifier;
	}

	public void setInputIdentifier(String inputIdentifier) {
		this.inputIdentifier = inputIdentifier;
	}

	public List<String> getCorrelationOptions() {
		return correlationOptions;
	}

	public void setCorrelationOptions(List<String> correlationOptions) {
		this.correlationOptions = correlationOptions;
	}

	public String getQueryComment() {
		return queryComment;
	}

	public void setQueryComment(String queryComment) {
		this.queryComment = queryComment;
	}

	public Map<String, Boolean> getExpressionStages() {
		return expressionStages;
	}

	public void setExpressionStages(Map<String, Boolean> expressionStages) {
		this.expressionStages = expressionStages;
	}
	
}
