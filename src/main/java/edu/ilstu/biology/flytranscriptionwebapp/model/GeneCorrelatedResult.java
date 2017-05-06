package edu.ilstu.biology.flytranscriptionwebapp.model;

public class GeneCorrelatedResult implements Comparable<GeneCorrelatedResult> {

	private Gene gene;
	private Double rVal;
	private Double pVal;

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public Double getrVal() {
		return rVal;
	}

	public void setrVal(Double rVal) {
		this.rVal = rVal;
	}

	public Double getpVal() {
		return pVal;
	}

	public void setpVal(Double pVal) {
		this.pVal = pVal;
	}

	@Override
	public int compareTo(GeneCorrelatedResult other) {
		// TODO: Unsure if this will work as expected. Will be easy to tell
		return Double.compare(other.rVal, this.rVal);
	}

}
