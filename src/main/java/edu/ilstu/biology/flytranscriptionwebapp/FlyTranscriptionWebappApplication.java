package edu.ilstu.biology.flytranscriptionwebapp;

import java.util.List;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.service.GenomeService;

@SpringBootApplication
public class FlyTranscriptionWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlyTranscriptionWebappApplication.class, args);
	}

	@Bean("genomeData")
	public List<Gene> genomeData(@Autowired GenomeService genomeService) {
		List<Gene> genomeList = genomeService.retrieveGenomeData();
		return genomeList;
	}
	
	@Bean
	public PearsonsCorrelation getPearsonsCorrelation(){
		return new PearsonsCorrelation();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return restTemplate;
	}
	
}
