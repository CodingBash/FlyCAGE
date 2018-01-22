package edu.ilstu.biology.flytranscriptionwebapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.lang3.StringUtils;
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

		// TODO: genomeList integrity checker
		for (Gene gene1 : genomeList) {
			int count = 0;
			for (Gene gene2 : genomeList) {
				if (gene1.getDbIdentifier().equals(gene2.getDbIdentifier())) {
					count++;
				}
			}
			if (count > 1) {
				System.out.println(gene1.getDbIdentifier() + " has count of " + count);
			}
		}

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
				.toArray(new CompletableFuture[geneRnaCompletableFutureResultList.size()])).join();

		List<GeneRNAInformationResultTO> geneRnaFirstResult = geneRnaCompletableFutureResultList.get(0).get();
		List<GeneRNAInformationResultTO> geneRnaResultList = new ArrayList<GeneRNAInformationResultTO>();
		if (!CollectionUtils.isEmpty(geneRnaFirstResult)) {
			// TODO: Check if prediction is close
			int geneRnaResultPredictSize = geneRnaFirstResult.size() * geneRnaCompletableFutureResultList.size();
			((ArrayList<GeneRNAInformationResultTO>) geneRnaResultList).ensureCapacity(geneRnaResultPredictSize);
			for (CompletableFuture<List<GeneRNAInformationResultTO>> geneRnaCompletableFutureResult : geneRnaCompletableFutureResultList) {
				boolean insertingFirst = true;
				for (GeneRNAInformationResultTO geneRnaResult : geneRnaCompletableFutureResult.get()) {
					/*
					 * Need to determine if there is an overlap between adjacent
					 * lists. If so, edit the previously inserted gene
					 * 
					 * TODO: Perhaps adjust the query so that size is not
					 * specifically on result rows, but on gene rows instead,
					 * thus avoiding overlap
					 */
					if (insertingFirst && !CollectionUtils.isEmpty(geneRnaResultList)) {
						if (StringUtils.equals(
								geneRnaResultList.get(geneRnaResultList.size() - 1).getPrimaryIdentifier(),
								geneRnaResult.getPrimaryIdentifier())) {
							geneRnaResultList.get(geneRnaResultList.size() - 1).getRnaSeqResults()
									.addAll(geneRnaResult.getRnaSeqResults());
						}
					} else {
						geneRnaResultList.add(geneRnaResult);
					}
					insertingFirst = false;
				}
				geneRnaResultList.addAll(geneRnaCompletableFutureResult.get());
			}
		}
		return CompletableFuture.completedFuture(geneRnaResultList);
	}

	@Async
	private CompletableFuture<List<GeneIDInformationResultTO>> retrieveGeneIdentifierInformationResults()
			throws InterruptedException, ExecutionException {
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
				.toArray(new CompletableFuture[geneIdentifierCompletableFutureResultList.size()])).join();

		List<GeneIDInformationResultTO> geneIdentifierFirstResult = geneIdentifierCompletableFutureResultList.get(0)
				.get();
		List<GeneIDInformationResultTO> geneIdentifierResultList = new ArrayList<GeneIDInformationResultTO>();
		if (!CollectionUtils.isEmpty(geneIdentifierFirstResult)) {

			// TODO: Check if prediction is close
			int geneIdentifierResultPredictSize = geneIdentifierFirstResult.size()
					* geneIdentifierCompletableFutureResultList.size();
			((ArrayList<GeneIDInformationResultTO>) geneIdentifierResultList)
					.ensureCapacity(geneIdentifierResultPredictSize);
			for (CompletableFuture<List<GeneIDInformationResultTO>> geneIdentifierCompletableFutureResult : geneIdentifierCompletableFutureResultList) {
				boolean insertingFirst = true;
				for (GeneIDInformationResultTO geneIdentifierResult : geneIdentifierCompletableFutureResult.get()) {
					/*
					 * Need to determine if there is an overlap between adjacent
					 * lists. If so, edit the previously inserted gene
					 * 
					 * TODO: Perhaps adjust the query so that size is not
					 * specifically on result rows, but on gene rows instead,
					 * thus avoiding overlap
					 */
					if (insertingFirst && !CollectionUtils.isEmpty(geneIdentifierResultList)) {
						if (StringUtils.equals(geneIdentifierResultList.get(geneIdentifierResultList.size() - 1)
								.getPrimaryIdentifier(), geneIdentifierResult.getPrimaryIdentifier())) {
							geneIdentifierResultList.get(geneIdentifierResultList.size() - 1).getSynonyms()
									.addAll(geneIdentifierResult.getSynonyms());
						}
					} else {
						geneIdentifierResultList.add(geneIdentifierResult);
					}
					insertingFirst = false;
				}
				geneIdentifierResultList.addAll(geneIdentifierCompletableFutureResult.get());
			}
		}
		return CompletableFuture.completedFuture(geneIdentifierResultList);
	}
}
