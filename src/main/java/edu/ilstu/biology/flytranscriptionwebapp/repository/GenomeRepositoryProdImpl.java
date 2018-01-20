package edu.ilstu.biology.flytranscriptionwebapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
//@Profile({ "production" })
public class GenomeRepositoryProdImpl implements GenomeRepository {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Object> retrieveGeneRnaData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> retrieveGeneIdentifierData() {
		// TODO Auto-generated method stub
		return null;
	}

}
