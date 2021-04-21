package com.chiang.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * 配置SentinelAspectConfiguration类后，Sentinel支持注解的控制资源
 * @author jianghao
 */
@Service
public class AnnotationService {
    /**
     * 原函数，若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。
     */
    @SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    public String hello(long s) {
        return String.format("Hello at %d", s);
    }

    /**
     * Fallback 函数，当资源方法异常时候会被调用。
     * 函数签名与原函数一致或加一个 Throwable 类型的参数.
     */
    public String helloFallback(long s,Throwable throwable) {
        return String.format("helloFallback %d", s);
    }

    /**
     * Block 函数，当资源调用超出配置的规则时候会被调用。
     * 参数最后多一个 BlockException，其余与原函数一致.
     */
    public String exceptionHandler(long s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "exceptionHandler error occurred at " + s;
    }
}