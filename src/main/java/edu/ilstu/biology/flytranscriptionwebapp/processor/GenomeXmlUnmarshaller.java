package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import edu.ilstu.biology.flytranscriptionwebapp.model.GeneIdentifierXml;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneRnaDataXml;
import edu.ilstu.biology.flytranscriptionwebapp.model.GenomeIdentifiersXml;
import edu.ilstu.biology.flytranscriptionwebapp.model.GenomeRnaDataXml;

@Deprecated
@Component
public class GenomeXmlUnmarshaller {

	private static final String GENOME_RNA_DATA_XML_FILE = "genome-rna-data-1.xml";
	private static final String GENOME_IDENTIFIERS_XML_FILE = "genome-identifiers-data-1.xml";

	public List<GeneRnaDataXml> unmarshallGenomeRnaDataFile() {
		List<GeneRnaDataXml> genomeData = null;
		try {
			File file = new ClassPathResource(GENOME_RNA_DATA_XML_FILE).getFile();
			// TODO: Fix the JAXB marhsalling
			// Resource:
			// http://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/
			JAXBContext jaxbContext = JAXBContext.newInstance(GenomeRnaDataXml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			GenomeRnaDataXml genomeDataXml = (GenomeRnaDataXml) jaxbUnmarshaller.unmarshal(file);
			genomeData = genomeDataXml.getGeneList();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}

		return (genomeData == null) ? Collections.emptyList() : genomeData;
	}

	public List<GeneIdentifierXml> unmarshallGenomeIdentifiersFile() {
		List<GeneIdentifierXml> genomeData = null;
		try {
			File file = new ClassPathResource(GENOME_IDENTIFIERS_XML_FILE).getFile();
			// TODO: Fix the JAXB marhsalling
			// Resource:
			// http://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/
			JAXBContext jaxbContext = JAXBContext.newInstance(GenomeIdentifiersXml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			GenomeIdentifiersXml genomeDataXml = (GenomeIdentifiersXml) jaxbUnmarshaller.unmarshal(file);
			genomeData = genomeDataXml.getGeneList();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}

		return (genomeData == null) ? Collections.emptyList() : genomeData;
	}

}
