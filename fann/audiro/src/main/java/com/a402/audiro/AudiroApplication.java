package com.a402.audiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class AudiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(AudiroApplication.class, args);
	}

}
