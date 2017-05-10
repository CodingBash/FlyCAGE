package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "genome-rna-data")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenomeRnaDataXml {

	@XmlElement(name = "gene", type = GeneRnaDataXml.class)
	private List<GeneRnaDataXml> geneList;

	public List<GeneRnaDataXml> getGeneList() {
		return geneList;
	}

	public void setGeneList(List<GeneRnaDataXml> geneList) {
		this.geneList = geneList;
	}

}
