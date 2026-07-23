package com.vikas.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

/**
 * Class      : RouteValidator
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Mar 3, 2026
 * Version    : 1.0
 */

@Component
public class RouteValidator {

	public static final List<String> openApiEndpoints = List.of(
			"/uploads/properties/**",
			"/rent-hub/api/v1/auth/phone/reset",
			"rent-hub/api/v1/auth/login",
			"rent-hub/api/v1/auth/phone/login-otp",
			"rent-hub/api/v1/auth/google" ,
			"rent-hub/api/v1/auth/register",
			"rent-hub/api/v1/auth/password-reset",
			"rent-hub/api/v1/auth/password-forgot",
			"/rent-hub/api/v1/auth/reset",
			"/rent-hub/api/v1/auth/jwt/refresh",
			"/rent-hub/api/v1/auth/password/forgot",
			
			"/rent-hub/api/v2/auth/phone/reset",
			"rent-hub/api/v2/auth/login",
			"rent-hub/api/v2/auth/phone/login-otp",
			"rent-hub/api/v2/auth/google" ,
			"rent-hub/api/v2/auth/register",
			"rent-hub/api/v2/auth/password-reset",
			"rent-hub/api/v2/auth/password-forgot",
			"/rent-hub/api/v2/auth/reset",
			"/rent-hub/api/v2/auth/jwt/refresh",
			"/rent-hub/api/v2/auth/password/forgot",
			
			
			
			"/swagger-ui/",
			"/v3/api-docs/",
			"/swagger-ui.html",
			"/instances/");

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
}
