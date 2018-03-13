package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.Map;

public class GeneForm {

	private String geneSpecies;

	private String inputIdentifier;

	private String genesOfInterest;

	private Integer geneResultCount;

	private String queryComment;

	private Map<String, Boolean> expressionStages;

	private String customExpressionUsed;
	
	private Map<String, Integer> customExpression;

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

	public String getGenesOfInterest() {
		return genesOfInterest;
	}

	public void setGenesOfInterest(String genesOfInterest) {
		this.genesOfInterest = genesOfInterest;
	}

	public Integer getGeneResultCount() {
		return geneResultCount;
	}

	public void setGeneResultCount(Integer geneResultCount) {
		this.geneResultCount = geneResultCount;
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

	public Map<String, Integer> getCustomExpression() {
		return customExpression;
	}

	public void setCustomExpression(Map<String, Integer> customExpression) {
		this.customExpression = customExpression;
	}

	public String getCustomExpressionUsed() {
		return customExpressionUsed;
	}

	public void setCustomExpressionUsed(String customExpressionUsed) {
		this.customExpressionUsed = customExpressionUsed;
	}

}
