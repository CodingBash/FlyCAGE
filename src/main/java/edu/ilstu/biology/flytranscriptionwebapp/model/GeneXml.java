package edu.ilstu.biology.flytranscriptionwebapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gene")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeneXml {

	@XmlElement(name = "gene-name")
	private String geneName;

	@XmlElement(name = "rna-exp")
	private String rnaData;

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getRnaData() {
		return rnaData;
	}

	public void setRnaData(String rnaData) {
		this.rnaData = rnaData;
	}

}
