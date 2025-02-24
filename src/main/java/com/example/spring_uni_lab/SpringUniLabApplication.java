package com.example.spring_uni_lab;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringUniLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUniLabApplication.class, args);

	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}


}
