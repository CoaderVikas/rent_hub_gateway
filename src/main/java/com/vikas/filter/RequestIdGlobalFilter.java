package com.vikas.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.UUID;
/**
 * Class      : RequestIdGlobalFilter
 * Description: [Add brief description here]
 * Author     : Vikas Yadav
 * Created On : Jul 13, 2026
 * Version    : 1.0
 */

@Component
@Slf4j
public class RequestIdGlobalFilter implements GlobalFilter, Ordered {

    private static final String REQUEST_ID_HEADER = "X-Request-Id";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String requestId = exchange.getRequest().getHeaders().getFirst(REQUEST_ID_HEADER);
        if (requestId == null || requestId.isEmpty()) {
            requestId = UUID.randomUUID().toString();
        }

        String finalRequestId = requestId;

        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header(REQUEST_ID_HEADER, finalRequestId)
                .build();

        exchange.getResponse().getHeaders().add(REQUEST_ID_HEADER, finalRequestId);

        log.info("[{}] Request received: {} {}", finalRequestId,
                exchange.getRequest().getMethod(), exchange.getRequest().getURI());

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    @Override
    public int getOrder() {
        return -2; // AuthenticationFilter se bhi pehle chalna chahiye
    }
}