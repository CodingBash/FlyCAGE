package edu.ilstu.biology.flytranscriptionwebapp.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "genome-gene-ids")
@XmlAccessorType(XmlAccessType.FIELD)
public class GenomeIdentifiersXml {

	@XmlElement(name = "gene", type = GeneIdentifierXml.class)
	private List<GeneIdentifierXml> geneList;

	public List<GeneIdentifierXml> getGeneList() {
		return geneList;
	}

	public void setGeneList(List<GeneIdentifierXml> geneList) {
		this.geneList = geneList;
	}

}
