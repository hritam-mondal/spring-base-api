package com.hritam.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "WeatherForecast", description = "Single day weather forecast")
public record WeatherForecast(
	@Schema(description = "Date of the forecast", example = "2025-10-31") LocalDate date,
	@Schema(description = "Temperature in Celsius", example = "23") int temperatureC,
	@Schema(description = "Summary text", example = "Mild") String summary
) {
	@JsonProperty("temperatureF")
	@Schema(description = "Temperature in Fahrenheit", example = "73")
	public int temperatureF() {
		return 32 + (int) Math.round(temperatureC * 9.0 / 5.0);
	}
}
