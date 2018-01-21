package edu.ilstu.biology.flytranscriptionwebapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
		CompletableFuture<List<GeneRNAInformationResultTO>> geneRnaCompletableFutureResult = retrieveGeneRnaInformationResults();
		CompletableFuture<List<GeneIDInformationResultTO>> geneIdentifierCompletableFutureResult = retrieveGeneIdentifierInformationResults();

		CompletableFuture.allOf(geneRnaCompletableFutureResult, geneIdentifierCompletableFutureResult).join();

		List<Gene> genomeList = genomeDataMapper.mapGenomicData(geneRnaCompletableFutureResult.get(),
				geneIdentifierCompletableFutureResult.get());

		return genomeList;
	}

	@Async
	private CompletableFuture<List<GeneRNAInformationResultTO>> retrieveGeneRnaInformationResults()
			throws InterruptedException, ExecutionException {
		int geneRnaDataCountFuture = genomeRepository.retrieveGeneRnaDataCount();
		int geneRnaTargetRunCount = 100; // TODO: Make this a configurable
											// variable?
		int geneRnaTargetResultCount = geneRnaDataCountFuture / geneRnaTargetRunCount;

		List<CompletableFuture<List<GeneRNAInformationResultTO>>> geneRnaCompletableFutureResultList = new ArrayList<CompletableFuture<List<GeneRNAInformationResultTO>>>(
				geneRnaTargetRunCount);
		for (int geneRnaQueryStart = 0; geneRnaQueryStart < geneRnaDataCountFuture; geneRnaQueryStart += geneRnaTargetResultCount) {
			geneRnaCompletableFutureResultList
					.add(genomeRepository.retrieveGeneRnaData(geneRnaQueryStart, geneRnaTargetResultCount));
		}
		CompletableFuture.allOf(geneRnaCompletableFutureResultList
				.toArray(new CompletableFuture[geneRnaCompletableFutureResultList.size()]));

		List<GeneRNAInformationResultTO> geneRnaFirstResult = geneRnaCompletableFutureResultList.get(0).get();
		List<GeneRNAInformationResultTO> geneRnaResult = new ArrayList<GeneRNAInformationResultTO>();
		if (CollectionUtils.isEmpty(geneRnaFirstResult)) {
			int geneRnaResultPredictSize = geneRnaFirstResult.size() * geneRnaCompletableFutureResultList.size();
			((ArrayList<GeneRNAInformationResultTO>) geneRnaResult).ensureCapacity(geneRnaResultPredictSize);
			for (CompletableFuture<List<GeneRNAInformationResultTO>> geneRnaCompletableFutureResult : geneRnaCompletableFutureResultList) {
				// TODO: Add into
			}
		}
		return CompletableFuture.completedFuture(geneRnaResult);
	}

	@Async
	private CompletableFuture<List<GeneIDInformationResultTO>> retrieveGeneIdentifierInformationResults() {
		int geneIdentifierDataCountFuture = genomeRepository.retrieveGeneIdentifierDataCount();
		int geneIdentifierTargetRunCount = 1; // TODO: Make this a configurable
												// variable?
		int geneIdentifierTargetResultCount = geneIdentifierDataCountFuture / geneIdentifierTargetRunCount;

		List<CompletableFuture<List<GeneIDInformationResultTO>>> geneIdentifierCompletableFutureResultList = new ArrayList<CompletableFuture<List<GeneIDInformationResultTO>>>(
				geneIdentifierTargetRunCount);
		for (int geneIdentifierQueryStart = 0; geneIdentifierQueryStart < geneIdentifierDataCountFuture; geneIdentifierQueryStart += geneIdentifierTargetResultCount) {
			geneIdentifierCompletableFutureResultList.add(genomeRepository
					.retrieveGeneIdentifierData(geneIdentifierQueryStart, geneIdentifierTargetResultCount));
		}
		CompletableFuture.allOf(geneIdentifierCompletableFutureResultList
				.toArray(new CompletableFuture[geneIdentifierCompletableFutureResultList.size()]));
		// TODO: Join all futures into one structure
		return null;
	}
}
