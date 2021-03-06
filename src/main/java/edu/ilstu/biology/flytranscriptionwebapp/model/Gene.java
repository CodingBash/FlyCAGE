package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

// TODO: Add field for the user input gene identifier
public class Gene {
	private String dbIdentifier;
	private String secondaryIdentifier;
	private String geneName;
	private List<String> synonyms;
	private int[] rnaExpData;
	
	public Gene(){
		super();
	}

	public Gene(Gene gene){
		this.dbIdentifier = gene.getDbIdentifier();
		this.secondaryIdentifier = gene.getSecondaryIdentifier();
		this.geneName = gene.getGeneName();
		this.synonyms = gene.getSynonyms();
		this.rnaExpData = gene.getRnaExpData();
	}

	public String getDbIdentifier() {
		return dbIdentifier;
	}

	public void setDbIdentifier(String dbIdentifier) {
		this.dbIdentifier = dbIdentifier;
	}

	public String getSecondaryIdentifier() {
		return secondaryIdentifier;
	}

	public void setSecondaryIdentifier(String secondaryIdentifier) {
		this.secondaryIdentifier = secondaryIdentifier;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public List<String> getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(List<String> synonyms) {
		this.synonyms = synonyms;
	}

	public int[] getRnaExpData() {
		return rnaExpData;
	}

	public void setRnaExpData(int[] rnaExpData) {
		this.rnaExpData = rnaExpData;
	}

}
