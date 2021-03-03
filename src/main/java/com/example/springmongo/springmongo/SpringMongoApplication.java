package com.example.springmongo.springmongo;

import com.example.springmongo.springmongo.repository.PegawaiRepository;
import com.example.springmongo.springmongo.repository.TutorialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.example.springmongo.springmongo.storage.StorageProperties;
import com.example.springmongo.springmongo.storage.StorageService;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.example.springmongo.springmongo.repository","com.example.springmongo.springmongo.storage","com.example.springmongo.springmongo.config"})
@EnableConfigurationProperties(StorageProperties.class)
public class SpringMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMongoApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
