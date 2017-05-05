package edu.ilstu.biology.flytranscriptionwebapp.processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import edu.ilstu.biology.flytranscriptionwebapp.model.GeneXml;
import edu.ilstu.biology.flytranscriptionwebapp.model.GenomeDataXml;

// TODO: Complete this logic
@Component
public class GenomeXmlUnmarshaller {
	public List<GeneXml> unmarshallFile() {
		try {
			File file = new ClassPathResource("genedata.xml").getFile();
			List<GeneXml> genomeData = new ArrayList<GeneXml>(17000);
			JAXBContext jaxbContext = JAXBContext.newInstance(GenomeDataXml.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			GenomeDataXml genomeDataXml = (GenomeDataXml) jaxbUnmarshaller.unmarshal(file);

		} catch (IOException | JAXBException e) {
			e.printStackTrace();
		}

		return new ArrayList<GeneXml>();
	}
}
