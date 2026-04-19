package com.vikas.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * 
 * Class      : CorsConfig
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Feb 18, 2026
 * Version    : 1.0
 */

@Configuration
public class CorsConfig implements WebFluxConfigurer  {

	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration config = new CorsConfiguration();
		// config.addAllowedOrigin("http://localhost:5173");
		config.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://127.0.0.1:5173",
				"https://overappreciative-unilludedly-larae.ngrok-free.dev"));
		config.addAllowedMethod("*"); // allows GET, POST, etc.
		config.addAllowedHeader("*");
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);

		return new CorsWebFilter(source);
	}
}
