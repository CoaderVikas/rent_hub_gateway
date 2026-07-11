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
			"/api/v1/auth/login",
			"/api/v1/auth/phone/login-otp",
			"/api/v1/auth/google" ,
			"/api/v1/auth/register",
			"/api/v1/auth/password-reset",
			"/api/v1/auth/password-forgot",
			"/api/v1/auth/refresh",
			"/rent-hub/auth/auth/login",
			"/rent-hub/auth/auth/register",
			"/api/v1/auth/reset",
			"/rent-hub/auth/refresh",
			//"/rent-hub/auth/password/forgot?username=" + "{username}",
			"/rent-hub/auth/password/forgot",
			"/swagger-ui/",
			"/v3/api-docs/",
			"/swagger-ui.html",
			"/instances/");

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
}
