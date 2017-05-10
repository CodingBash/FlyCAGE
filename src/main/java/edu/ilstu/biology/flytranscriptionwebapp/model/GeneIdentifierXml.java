package edu.ilstu.biology.flytranscriptionwebapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gene")
@XmlAccessorType(XmlAccessType.FIELD)
public class GeneIdentifierXml {

	@XmlElement(name = "gene-db-id")
	private String dbIdentifier;

	@XmlElement(name = "gene-sec-id")
	private String secondaryIdentifier;

	@XmlElement(name = "gene-name")
	private String geneName;

	@XmlElement(name = "synonyms")
	private String synonyms;

	public String getDbIdentifier() {
		return dbIdentifier;
	}

	public void setDbIdentifier(String dbIdentifier) {
		this.dbIdentifier = dbIdentifier;
	}

	public String getSecondaryIdentifier() {
		return secondaryIdentifier;
	}

	public void setSecondaryIdentifier(String secondaryIdentifier) {
		this.secondaryIdentifier = secondaryIdentifier;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

}
