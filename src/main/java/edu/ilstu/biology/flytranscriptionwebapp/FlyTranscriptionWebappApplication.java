package edu.ilstu.biology.flytranscriptionwebapp;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import edu.ilstu.biology.flytranscriptionwebapp.mapper.GenomeDataMapper;
import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;
import edu.ilstu.biology.flytranscriptionwebapp.processor.RetrieveExpressionStages;
import edu.ilstu.biology.flytranscriptionwebapp.service.GenomeService;

@EnableAsync
@SpringBootApplication
public class FlyTranscriptionWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlyTranscriptionWebappApplication.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(85);
		executor.setThreadNamePrefix("FlyCAGE-");
		executor.initialize();
		return executor;
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		return restTemplate;
	}

	@Profile({ "development" })
	@Bean("genomeData")
	public List<Gene> genomeDataDevelopment(@Autowired GenomeDataMapper genomeDataMapper) {
		List<Gene> genomeList = genomeDataMapper.mapGenomicData();
		return genomeList;
	}

	@DependsOn({ "asyncExecutor", "restTemplate", "allExpressionStages" })
	@Profile({ "production", "default" })
	@Bean("genomeData")
	public List<Gene> genomeDataProduction(@Autowired GenomeService genomeService)
			throws InterruptedException, ExecutionException {
		System.out.println("NOW CALLING INTERMINE API");
		List<Gene> genomeList = genomeService.retrieveGenomeData();
		System.out.println("CALL COMPLETE");
		return genomeList;
	}

	@Bean
	public PearsonsCorrelation getPearsonsCorrelation() {
		return new PearsonsCorrelation();
	}

	@Bean
	@Qualifier("allExpressionStages")
	public List<String> allExpressionStages(@Autowired RetrieveExpressionStages retrieveExpressionStages) {
		return retrieveExpressionStages.getDmelanogasterExpressionStages();
	}

}
