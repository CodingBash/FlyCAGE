package edu.ilstu.biology.flytranscriptionwebapp.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneIDInformationResultTO;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneRNAInformationResultTO;

public interface GenomeRepository {

	public CompletableFuture<List<GeneRNAInformationResultTO>> retrieveGeneRnaData();
	
	public CompletableFuture<List<GeneIDInformationResultTO>> retrieveGeneIdentifierData();
	
}
