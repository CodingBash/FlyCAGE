package edu.ilstu.biology.flytranscriptionwebapp.model;

public class GeneCorrelatedResult implements Comparable<GeneCorrelatedResult> {

	private Gene gene;
	private CorrelationResult corrResult;
	private int rank;

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

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int compareTo(GeneCorrelatedResult other) {
		return Double.compare(other.corrResult.getrVal(), this.corrResult.getrVal());
		/*
		 * TODO: Get compareTo to work. It's like this logic doesn't even make
		 * an impact TODO: Actually, this may be unneeded. Results seems to be
		 * sorted fine
		 */
		/*
		 * int rValCompared =
		 * Double.compare(Double.valueOf(other.corrResult.getrValSigFigs()),
		 * Double.valueOf(this.corrResult.getrValSigFigs())); if (rValCompared
		 * == 0) { System.out.println("here");
		 * System.out.println(other.corrResult.getrValSigFigs());
		 * System.out.println(this.corrResult.getrValSigFigs()); int
		 * ciValCompared = -1 *
		 * Double.compare(Double.valueOf(other.corrResult.getCiValSigFigs()),
		 * Double.valueOf(this.corrResult.getCiValSigFigs())); if (ciValCompared
		 * == 0) { return
		 * Double.compare(Double.valueOf(other.corrResult.getpValSigFigs()),
		 * Double.valueOf(this.corrResult.getpValSigFigs())); } else { return
		 * ciValCompared; } } else { return rValCompared; }
		 */
	}

}
