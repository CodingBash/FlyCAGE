package edu.ilstu.biology.flytranscriptionwebapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GeneXml {

	private String geneName;
	private String rnaData;

	@XmlElement(name = "gene-name")
	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	@XmlElement(name = "rna-exp")
	public String getRnaData() {
		return rnaData;
	}

	public void setRnaData(String rnaData) {
		this.rnaData = rnaData;
	}

}
