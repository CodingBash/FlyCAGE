package edu.ilstu.biology.flytranscriptionwebapp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneXml;
import edu.ilstu.biology.flytranscriptionwebapp.model.GenomeDataXml;
import edu.ilstu.biology.flytranscriptionwebapp.processor.GenomeXmlUnmarshaller;

@Component
public class GenomeDataMapper {

	@Autowired
	private GenomeXmlUnmarshaller genomeXmlUnmarshaller; 
	
	public List<Gene> mapGenomicData() {
		List<Gene> geneList = new ArrayList<Gene>();
		List<GeneXml> geneXmlList = genomeXmlUnmarshaller.unmarshallFile();

		for (GeneXml geneXml : geneXmlList) {
			Gene gene = new Gene();
			gene.setGeneName(geneXml.getGeneName());
			int[] rnaExp = new int[104];
			String[] rnaDataXml = StringUtils.split(geneXml.getRnaData(), ",");
			for (int i = 0; i < rnaDataXml.length; i++) {
				rnaExp[i] = Integer.valueOf(rnaDataXml[i]);
			}
			gene.setRnaExpData(rnaExp);
		}
		return geneList;
	}
}
