package edu.ilstu.biology.flytranscriptionwebapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gene")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeneRnaDataXml {

	@XmlElement(name = "gene-db-id")
	private String dbIdentifier;

	@XmlElement(name = "rna-exp")
	private String rnaData;

	public String getDbIdentifier() {
		return dbIdentifier;
	}

	public void setDbIdentifier(String dbIdentifier) {
		this.dbIdentifier = dbIdentifier;
	}

	public String getRnaData() {
		return rnaData;
	}

	public void setRnaData(String rnaData) {
		this.rnaData = rnaData;
	}

}
