package com.vikas.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.vikas.exception.RentHubServiceException;
import com.vikas.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Class : AuthenticationFilter Description: Custom Gateway Filter to validate
 * JWT token for secured routes. Author : Vikas Yadav Created On : Mar 3, 2026
 * Version : 1.1
 */

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	@Autowired
	private RouteValidator validator;

	@Autowired
	private JwtUtil jwtUtil;

	public AuthenticationFilter() {
		super(Config.class);
	}

	/**
	 * Apply method executes for every incoming request
	 */
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {

			log.info("********* Incoming request to URI: {} *********", exchange.getRequest().getURI());

			// Step 1: Check if route is secured
			if (validator.isSecured.test(exchange.getRequest())) {

				log.info("********* Secured route detected. Performing authentication check. *********");

				// Step 2: Validate Authorization header presence
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					log.error("********* Authorization header is missing *********");
					throw new RentHubServiceException("Missing Authorization Header");
				}

				// Step 3: Extract token from Authorization header
				String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					authHeader = authHeader.substring(7);
				}

				// Step 4: Validate JWT token
				try {
					jwtUtil.validateToken(authHeader);
					log.info("********* JWT token validation successful ********");

				} catch (Exception e) {
					log.error("********* Invalid JWT token: {} *********", e.getMessage());
					throw new RentHubServiceException("Unauthorized access to application");
				}
			}
			// Step 5: Continue filter chain
			return chain.filter(exchange);
		});
	}

	/**
	 * Configuration class (Required by AbstractGatewayFilterFactory)
	 */
	public static class Config {
		// Add custom configuration properties if needed
	}
}