package edu.ilstu.biology.flytranscriptionwebapp.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneIDInformationResultTO;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneIDInformationTO;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneRNAInformationResultTO;
import edu.ilstu.biology.flytranscriptionwebapp.transferobject.GeneRNAInformationTO;

@Repository
@PropertySource("classpath:queries.properties")
public class GenomeRepositoryImpl implements GenomeRepository {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${queries.rna}")
	private String geneRnaQuery;

	@Value("${queries.id}")
	private String geneIdQuery;

	private static final String QUERY_URL = "http://www.flymine.org/query/service/query/results";

	@Async
	@Override
	public CompletableFuture<List<GeneRNAInformationResultTO>> retrieveGeneRnaData() {

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("format", "jsonobjects");
		body.add("query", geneRnaQuery);
		body.add("size", Integer.toString(500));
		HttpEntity<?> httpEntity = new HttpEntity<Object>(body, new HttpHeaders());

		ResponseEntity<GeneRNAInformationTO> response = restTemplate.exchange(QUERY_URL, HttpMethod.POST, httpEntity,
				GeneRNAInformationTO.class);

		return CompletableFuture.completedFuture(response.getBody().getResults());
	}

	@Async
	@Override
	public CompletableFuture<List<GeneIDInformationResultTO>> retrieveGeneIdentifierData() {

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("format", "jsonobjects");
		body.add("query", geneIdQuery);
		body.add("size", Integer.toString(500));
		HttpEntity<?> httpEntity = new HttpEntity<Object>(body, new HttpHeaders());

		ResponseEntity<GeneIDInformationTO> response = restTemplate.exchange(QUERY_URL, HttpMethod.POST, httpEntity,
				GeneIDInformationTO.class);

		return CompletableFuture.completedFuture(response.getBody().getResults());
	}
}
