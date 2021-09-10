package com.gateway.demo;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * 通过请求springboot项目com.spring.gateway包下的GatewayApplication，制作延迟调用fallback方法
 * @author Chiang
 */
@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
@RestController
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration) {
		String httpUri = uriConfiguration.getHttpUrl();
		return builder.routes()
			.route(p -> p
				.path("/post")
				.filters(gatewayFilterSpec -> {
					gatewayFilterSpec.addRequestHeader("Hello", "World")
					.modifyRequestBody(String.class,String.class,((serverWebExchange, s) ->{

						// 将RequestBody的信息保存起来，
						serverWebExchange.getAttributes().put("requestBody",s);

						return Mono.just(s.toUpperCase());
					}
					))
					.modifyResponseBody(String.class, String.class, (serverWebExchange, s) -> {

						// 打印请求的信息
						ServerHttpRequest request = serverWebExchange.getRequest();

						HttpHeaders headers = request.getHeaders();
						Set<String> stringSet = headers.keySet();
						for (String s1 : stringSet) {
							System.out.println(s1 + "="+ headers.get(s1));
						}

						System.out.println("path:"+request.getPath());
						System.out.println("URI:"+request.getURI());
						System.out.println("params:"+request.getQueryParams());
						System.out.println("method："+request.getMethod());
						int status = Objects.requireNonNull(serverWebExchange.getResponse().getStatusCode()).value();
						System.out.println("status:"+status);


						System.out.println("requestBody" + serverWebExchange.getAttributes().get("requestBody").toString());

						return Mono.just(s);
					});
					return gatewayFilterSpec;
				})
				.uri(httpUri))
			.route(p -> p
				.host("*.hystrix.com")
				.filters(f -> f
					.hystrix(config -> config
						.setName("mycmd")
						.setFallbackUri("forward:/fallback"))) //默认超过1秒会调用应急方法
				.uri(httpUri))
			.build();
	}

	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("two fallback");
	}

	/**
	 * 全局过滤器，可以获取到请求的内容
	 */
	@Bean
	public GlobalFilter apply() {
		return (exchange, chain) -> {
			ModifyRequestBodyGatewayFilterFactory.Config modifyRequestConfig = new ModifyRequestBodyGatewayFilterFactory.Config();

			modifyRequestConfig.setRewriteFunction(String.class, String.class, (exchange1, originalRequestBody) -> {
				System.out.println("apply-请求的内容:"+originalRequestBody);
				return Mono.just(originalRequestBody);
			});

			return new ModifyRequestBodyGatewayFilterFactory().apply(modifyRequestConfig).filter(exchange, chain);
		};
	}

}