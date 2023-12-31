package com.fomov.itbootcamptesttask.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.fomov.itbootcamptesttask")
@EntityScan("com.fomov.itbootcamptesttask.data.model")
@EnableJpaRepositories("com.fomov.itbootcamptesttask.data.repository")
@EnableTransactionManagement
public class ItBootcampTestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItBootcampTestTaskApplication.class, args);
	}

}

