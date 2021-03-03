package com.example.springmongo.springmongo;

import com.example.springmongo.springmongo.storage.StorageProperties;
import com.example.springmongo.springmongo.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootTest
class SpringMongoApplicationTests {

	@Test
	void contextLoads() throws Exception {
	}

}
