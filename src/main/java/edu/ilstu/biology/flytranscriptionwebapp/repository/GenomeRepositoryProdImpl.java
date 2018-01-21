package edu.ilstu.biology.flytranscriptionwebapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Repository
// @Profile({ "production" })
@PropertySource("classpath:queries.properties")
public class GenomeRepositoryProdImpl implements GenomeRepository {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${queries.rna}")
	private String geneRnaQuery;

	@Value("${queries.id}")
	private String geneIdQuery;

	private static final String QUERY_URL = "http://www.flymine.org/query/service/query/results";

	@Override
	public List<Object> retrieveGeneRnaData() {

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("format", "json");
		body.add("query", geneRnaQuery);
		body.add("size", Integer.toString(10));
		HttpEntity<?> httpEntity = new HttpEntity<Object>(body, new HttpHeaders());

		ResponseEntity<String> response = restTemplate.exchange(QUERY_URL, HttpMethod.POST, httpEntity,
				String.class);

		return null;
	}

	@Override
	public List<Object> retrieveGeneIdentifierData() {

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("format", "json");
		body.add("query", geneIdQuery);
		body.add("size", Integer.toString(10));
		HttpEntity<?> httpEntity = new HttpEntity<Object>(body, new HttpHeaders());

		ResponseEntity<String> response = restTemplate.exchange(QUERY_URL, HttpMethod.POST, httpEntity,
				String.class);

		return null;
	}

}
