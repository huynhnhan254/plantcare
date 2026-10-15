package com.plantcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching 
public class PlantcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlantcareApplication.class, args);
	}

}
