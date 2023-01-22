package com.example.kafkaforpractice;

import com.example.kafkaforpractice.configuration.CustomProperties;
import com.example.kafkaforpractice.kafka.KafkaService;
import com.example.kafkaforpractice.kafka.trusteddata.Data;
import com.example.kafkaforpractice.kafka.trusteddata.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
@EnableConfigurationProperties(CustomProperties.class)
public class KafkaForPracticeApplication implements ApplicationRunner {

	@Autowired
	private KafkaService kafkaService;

	public static void main(String[] args) {
		SpringApplication.run(KafkaForPracticeApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		Data data = new Data("Test", 0);
		kafkaService.mySend(data);

		char[][] chars = {
				{'@', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', 'X'}};

		Map map = new Map(chars);
		kafkaService.sendCharMap(map);
	}
}
