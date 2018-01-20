package edu.ilstu.biology.flytranscriptionwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.repository.GenomeRepository;

@Service
public class GenomeService {
	
	@Autowired
	GenomeRepository genomeRepository;

	public List<Gene> retrieveGenomeData(){
		List<Object> geneRnaData = genomeRepository.retrieveGeneRnaData();
		List<Object> geneIdentiferData = genomeRepository.retrieveGeneIdentifierData();
		return null;
	}
}
