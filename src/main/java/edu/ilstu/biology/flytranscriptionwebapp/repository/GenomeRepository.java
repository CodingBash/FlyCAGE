package edu.ilstu.biology.flytranscriptionwebapp.repository;

import java.util.List;

public interface GenomeRepository {

	public List<Object> retrieveGeneRnaData();
	
	public List<Object> retrieveGeneIdentifierData();
	
}
