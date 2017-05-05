package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "genome")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenomeDataXml {

	@XmlElement(name = "gene")
	private List<GeneXml> geneList;
	
	public List<GeneXml> getGeneList() {
		return geneList;
	}

	public void setGeneList(List<GeneXml> geneList) {
		this.geneList = geneList;
	}
	
	
}
