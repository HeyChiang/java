package com.gateway.demo;

import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.http.HttpStatus;
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

        System.out.println(HttpStatus.OK.equals(exchange.getResponse().getStatusCode()));
        System.out.println(HttpStatus.OK == exchange.getResponse().getStatusCode());

        System.out.println("响应状态："+exchange.getResponse().getStatusCode());

        String originalBody = body == null ? "" : new String(body);
        System.out.println("BodyRewrite body：" + originalBody);

        return Mono.just(originalBody.getBytes());
    }
}