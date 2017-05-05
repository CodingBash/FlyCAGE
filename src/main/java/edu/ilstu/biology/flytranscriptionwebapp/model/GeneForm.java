package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

public class GeneForm {

	private String geneSpecies;

	private String geneName;

	private List<String> correlationOptions;

	private String queryComment;

	public String getGeneSpecies() {
		return geneSpecies;
	}

	public void setGeneSpecies(String geneSpecies) {
		this.geneSpecies = geneSpecies;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
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

}
