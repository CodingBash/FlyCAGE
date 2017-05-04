package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "genome")	
public class GenomeDataXml {

	private List<GeneXml> geneList;

	@XmlElement(name = "gene")
	public List<GeneXml> getGeneList() {
		return geneList;
	}

	public void setGeneList(List<GeneXml> geneList) {
		this.geneList = geneList;
	}
	
	
}
