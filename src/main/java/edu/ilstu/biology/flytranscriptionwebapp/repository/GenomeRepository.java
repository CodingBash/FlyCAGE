package edu.ilstu.biology.flytranscriptionwebapp.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneIDInformationResultTO;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneRNAInformationResultTO;

public interface GenomeRepository {

	public CompletableFuture<List<GeneRNAInformationResultTO>> retrieveGeneRnaData(int queryStart, int querySize);
	
	public CompletableFuture<List<GeneIDInformationResultTO>> retrieveGeneIdentifierData(int queryStart, int querySize);

	public Integer retrieveGeneRnaDataCount();

	public Integer retrieveGeneIdentifierDataCount();

	
	
}
