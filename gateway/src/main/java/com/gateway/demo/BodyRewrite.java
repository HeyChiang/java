package com.gateway.demo;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class BodyRewrite implements RewriteFunction<byte[], byte[]> {
    @Override
    public Publisher<byte[]> apply(ServerWebExchange exchange, byte[] body) {
        String originalBody = body == null ? "" : new String(body);

        System.out.println("BodyRewrite - 返回的内容："+originalBody);

        if (!ServerWebExchangeUtils.isAlreadyRouted(exchange)) {
            return Mono.just(originalBody.getBytes());
        } else {
            // its the reponse body when already routed
            return Mono.just(originalBody.getBytes(StandardCharsets.UTF_8));
        }
    }
}