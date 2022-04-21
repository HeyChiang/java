package com.dubbo.provider.filter;

import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.rpc.*;

/**
 * Dubbo过滤器，定义后可以放在 @DubboService 的filter里，起到一个服务的过滤作用。
 *
 * 如果放在yml中dubbo.provider.filter 里就对整个Dubbo服务的调用过滤
 */
@Log4j2
public class ApacheDubboFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        log.info("进入了ApacheDubboFilter ");

        Result result = invoker.invoke(invocation);

        log.info("调用了 ApacheDubboFilter " +result.toString());

        return result;
    }
}
