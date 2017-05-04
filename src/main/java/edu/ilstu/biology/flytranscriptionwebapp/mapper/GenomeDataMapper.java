package edu.ilstu.biology.flytranscriptionwebapp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.model.GeneXml;
import edu.ilstu.biology.flytranscriptionwebapp.model.GenomeDataXml;

@Component
public class GenomeDataMapper {

	public List<Gene> mapGenomicData(GenomeDataXml genomeDataXml) {
		List<Gene> geneList = new ArrayList<Gene>();
		List<GeneXml> geneXmlList = genomeDataXml.getGeneList();

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
