package com.smartcn.smartcndashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class SmartcndashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartcndashboardApplication.class, args);
		System.out.println("In Main");
	}
}
