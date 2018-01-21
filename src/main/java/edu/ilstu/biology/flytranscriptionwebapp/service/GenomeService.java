package edu.ilstu.biology.flytranscriptionwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.repository.GenomeRepository;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneIDInformationResultTO;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneRNAInformationResultTO;

@Service
public class GenomeService {
	
	@Autowired
	private GenomeRepository genomeRepository;

	public List<Gene> retrieveGenomeData(){
		List<GeneRNAInformationResultTO> geneRnaData = genomeRepository.retrieveGeneRnaData();
		System.out.println(geneRnaData);
		List<GeneIDInformationResultTO> geneIdentiferData = genomeRepository.retrieveGeneIdentifierData();
		System.out.println(geneIdentiferData);
		return null;
	}
}
