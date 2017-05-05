package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import edu.ilstu.biology.flytranscriptionwebapp.model.GeneXml;
import edu.ilstu.biology.flytranscriptionwebapp.model.GenomeDataXml;

@Component
public class GenomeXmlUnmarshaller {
	
	public List<GeneXml> unmarshallFile() {
		List<GeneXml> genomeData = null;
		try {
			File file = new ClassPathResource("genedata.xml").getFile();
			// TODO: Fix the JAXB marhsalling
			// Resource: http://howtodoinjava.com/jaxb/jaxb-exmaple-marshalling-and-unmarshalling-list-or-set-of-objects/
			JAXBContext jaxbContext = JAXBContext.newInstance(GenomeDataXml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			GenomeDataXml genomeDataXml = (GenomeDataXml) jaxbUnmarshaller.unmarshal(file);
			genomeData = genomeDataXml.getGeneList();
		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}

		return (genomeData == null) ? Collections.emptyList() : genomeData;
	}
	
}
