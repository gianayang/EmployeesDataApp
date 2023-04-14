package com.api.employeesdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EmployeesDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeesDataApplication.class, args);
	}
}
