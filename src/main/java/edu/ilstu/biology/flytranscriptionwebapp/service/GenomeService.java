package edu.ilstu.biology.flytranscriptionwebapp.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ilstu.biology.flytranscriptionwebapp.mapper.GenomeDataMapper;
import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.repository.GenomeRepository;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneIDInformationResultTO;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneRNAInformationResultTO;

@Service
public class GenomeService {
	
	@Autowired
	private GenomeRepository genomeRepository;
	
	@Autowired
	private GenomeDataMapper genomeDataMapper;

	public List<Gene> retrieveGenomeData() throws InterruptedException, ExecutionException{
		CompletableFuture<List<GeneRNAInformationResultTO>> geneRnaData = genomeRepository.retrieveGeneRnaData();
		CompletableFuture<List<GeneIDInformationResultTO>> geneIdentifierData = genomeRepository.retrieveGeneIdentifierData();
		CompletableFuture.allOf(geneRnaData, geneIdentifierData).join();
		List<Gene> genomeList = genomeDataMapper.mapGenomicData(geneRnaData.get(), geneIdentifierData.get());
		return genomeList;
	}
}
