package edu.ilstu.biology.flytranscriptionwebapp.repository;

import java.util.List;

import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneIDInformationResultTO;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneRNAInformationResultTO;

public interface GenomeRepository {

	public List<GeneRNAInformationResultTO> retrieveGeneRnaData();
	
	public List<GeneIDInformationResultTO> retrieveGeneIdentifierData();
	
}
