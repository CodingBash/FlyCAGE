package edu.ilstu.biology.flytranscriptionwebapp.model;

public class Gene {
	private String geneName;
	private int[] rnaExpData;

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
