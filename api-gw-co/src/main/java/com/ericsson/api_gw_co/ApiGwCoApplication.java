package com.ericsson.api_gw_co;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class ApiGwCoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGwCoApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
