package io.github.mshivaeifar.telemetryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@SpringBootApplication
public class TelemetryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelemetryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner mappings(RequestMappingHandlerMapping mapping) {
		return args -> mapping.getHandlerMethods()
				.forEach((key, value) -> System.out.println(key));
	}

}
