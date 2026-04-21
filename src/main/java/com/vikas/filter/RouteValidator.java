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
			"/api/v1/auth/login",
			"/api/v1/auth/register",
			"/rent-hub/auth/auth/login",
			"/rent-hub/auth/auth/register",
			"/rent-hub/auth/password/reset",
			//"/rent-hub/auth/password/forgot?username=" + "{username}",
			"/rent-hub/auth/password/forgot",
			"/swagger-ui/",
			"/v3/api-docs/",
			"/swagger-ui.html",
			"/instances/");

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));
}
