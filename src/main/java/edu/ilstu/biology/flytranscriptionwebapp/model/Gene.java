package edu.ilstu.biology.flytranscriptionwebapp.model;

public class Gene {
	private String dbIdentifier;
	private String secondaryIdentifier;
	private String geneName;
	private int[] rnaExpData;

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

	public int[] getRnaExpData() {
		return rnaExpData;
	}

	public void setRnaExpData(int[] rnaExpData) {
		this.rnaExpData = rnaExpData;
	}

}
