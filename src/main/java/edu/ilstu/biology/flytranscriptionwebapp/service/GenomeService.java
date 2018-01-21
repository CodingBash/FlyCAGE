package edu.ilstu.biology.flytranscriptionwebapp.service;

import java.util.ArrayList;
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

	public List<Gene> retrieveGenomeData() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> geneRnaDataCountFuture = genomeRepository.retrieveGeneRnaDataCount();
		CompletableFuture<Integer> geneIdentifierDataCountFuture = genomeRepository.retrieveGeneIdentifierDataCount();
		CompletableFuture.allOf(geneRnaDataCountFuture, geneIdentifierDataCountFuture).join();

		/*
		 * TODO: Make this configuration variables?
		 */
		int geneRnaTargetRunCount = 100;
		int geneIdentifierTargetRunCount = 1;
		int geneRnaTargetResultCount = geneRnaDataCountFuture.get() / geneRnaTargetRunCount;
		int geneIdentifierTargetResultCount = geneIdentifierDataCountFuture.get() / geneIdentifierTargetRunCount;

		List<CompletableFuture<List<GeneRNAInformationResultTO>>> geneRnaCompletableFutureResultList = new ArrayList<CompletableFuture<List<GeneRNAInformationResultTO>>>(geneRnaTargetRunCount);
		for (int geneRnaQueryStart = 0; geneRnaQueryStart < geneRnaDataCountFuture
				.get(); geneRnaQueryStart += geneRnaTargetResultCount) {
			// TODO: WS call
		}
		CompletableFuture.allOf(geneRnaCompletableFutureResultList.toArray(new CompletableFuture[geneRnaCompletableFutureResultList.size()]));
		// TODO: Join all futures into one structure
		
		List<CompletableFuture<List<GeneIDInformationResultTO>>> geneIdentifierCompletableFutureResultList = new ArrayList<CompletableFuture<List<GeneIDInformationResultTO>>>(geneIdentifierTargetRunCount);
		for (int geneIdentifierQueryStart = 0; geneIdentifierQueryStart < geneIdentifierDataCountFuture
				.get(); geneIdentifierQueryStart += geneIdentifierTargetResultCount) {
			//TODO: WS Call
		}
		CompletableFuture.allOf(geneIdentifierCompletableFutureResultList.toArray(new CompletableFuture[geneIdentifierCompletableFutureResultList.size()]));
		// TODO: Join all futures into one structure		
		
		//List<Gene> genomeList = genomeDataMapper.mapGenomicData(geneRnaDataFuture.get(),
		//		geneIdentifierDataFuture.get());
		//return genomeList;
		return null;
	}
}
