package com.mealdb.explorer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MealDbExplorerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MealDbExplorerApiApplication.class, args);
	}

}
