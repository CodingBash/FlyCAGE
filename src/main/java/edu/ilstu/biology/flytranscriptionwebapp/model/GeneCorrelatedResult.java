package edu.ilstu.biology.flytranscriptionwebapp.model;

public class GeneCorrelatedResult implements Comparable<GeneCorrelatedResult> {

	private Gene gene;
	private CorrelationResult corrResult;

	public Gene getGene() {
		return gene;
	}

	public void setGene(Gene gene) {
		this.gene = gene;
	}

	public CorrelationResult getCorrResult() {
		return corrResult;
	}

	public void setCorrResult(CorrelationResult corrResult) {
		this.corrResult = corrResult;
	}

	@Override
	public int compareTo(GeneCorrelatedResult other) {
		return Double.compare(other.corrResult.getrVal(), this.corrResult.getrVal());
	}

}
