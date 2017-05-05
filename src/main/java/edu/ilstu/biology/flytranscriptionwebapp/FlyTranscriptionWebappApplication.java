package edu.ilstu.biology.flytranscriptionwebapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.ilstu.biology.flytranscriptionwebapp.mapper.GenomeDataMapper;
import edu.ilstu.biology.flytranscriptionwebapp.model.Gene;

@SpringBootApplication
public class FlyTranscriptionWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlyTranscriptionWebappApplication.class, args);
	}

	@Bean("genomeData")
	public List<Gene> genomeData(@Autowired GenomeDataMapper genomeDataMapper) {
		List<Gene> genomeList = genomeDataMapper.mapGenomicData();
		System.out.println("breaker");
		return genomeList;
	}
}
