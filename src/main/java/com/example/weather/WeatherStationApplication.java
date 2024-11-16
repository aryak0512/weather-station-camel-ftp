package com.example.weather;

import com.example.weather.utils.FilterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class WeatherStationApplication implements CommandLineRunner {

	private final FilterService filterService;

	public WeatherStationApplication(FilterService filterService) {
		this.filterService = filterService;
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherStationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		filterService.populate();
	}
}
