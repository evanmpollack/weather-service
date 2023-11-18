package com.evanm.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WeatherappApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherappApplication.class, args);
	}

}
