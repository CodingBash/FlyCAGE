package edu.ilstu.biology.flytranscriptionwebapp;

import java.util.List;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import edu.ilstu.biology.flytranscriptionwebapp.mapper.GenomeDataMapper;
import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.service.GenomeService;

@SpringBootApplication
public class FlyTranscriptionWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlyTranscriptionWebappApplication.class, args);
	}

	@Profile({"development", "default"})
	@Bean("genomeData")
	public List<Gene> genomeDataDevelopment(@Autowired GenomeDataMapper genomeDataMapper) {
		List<Gene> genomeList = genomeDataMapper.mapGenomicData();
		return genomeList;
	}
	

	@Profile("production")
	@Bean("genomeData")
	public List<Gene> genomeDataProduction(@Autowired GenomeService genomeService) {
		System.out.println("NOW CALLING INTERMINE API");
		List<Gene> genomeList = genomeService.retrieveGenomeData();
		System.out.println("CALL COMPLETE");
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
