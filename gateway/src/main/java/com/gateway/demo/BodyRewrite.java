package com.gateway.demo;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 重写内容
 *
 * @author jianghao
 */
@Component
public class BodyRewrite implements RewriteFunction<byte[], byte[]> {
    @Override
    public Publisher<byte[]> apply(ServerWebExchange exchange, byte[] body) {

        String originalBody = body == null ? "" : new String(body);
        System.out.println("BodyRewrite body：" + originalBody);

        return Mono.just(originalBody.getBytes());
    }
}