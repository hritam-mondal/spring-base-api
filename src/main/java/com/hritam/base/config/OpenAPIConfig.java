package com.hritam.base.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

	@Value("${app.openapi.title:Weather Forecast API}")
	private String title;

	@Value("${app.openapi.description:WeatherForecast API}")
	private String description;

	@Value("${app.openapi.version:1.0.0}")
	private String version;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.components(new Components())
			.info(new Info()
				.title(title)
				.version(version)
				.description(description)
				.contact(new Contact().name("Hritam Mondal"))
				.license(new License().name("MIT").url("https://opensource.org/licenses/MIT")));
	}
}