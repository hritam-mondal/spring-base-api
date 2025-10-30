package com.hritam.base.controller;

import com.hritam.base.model.WeatherForecast;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.SplittableRandom;

@RestController
@RequestMapping("/weatherforecast")
@Tag(name = "Weather", description = "Weather forecast operations")
public class WeatherController {

	private static final String[] SUMMARIES = {
		"Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
	};

	private static final SplittableRandom RANDOM = new SplittableRandom();

	@Operation(summary = "Get 5-day forecast", description = "Returns a list of five generated weather forecasts")
	@GetMapping
	public WeatherForecast[] getWeatherForecast() {
		LocalDate today = LocalDate.now();
		WeatherForecast[] forecasts = new WeatherForecast[5];
		for (int i = 0; i < 5; i++) {
			forecasts[i] = new WeatherForecast(
				today.plusDays(i + 1),
				RANDOM.nextInt(-20, 55),
				SUMMARIES[RANDOM.nextInt(SUMMARIES.length)]
			);
		}
		return forecasts;
	}
}
