package com.example.studygraph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudygraphApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudygraphApplication.class, args);
		FalkorDBService service = new FalkorDBService();
		service.test();
	}

}
