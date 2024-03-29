package com.mybatis.intercept;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * 读写分离自动拦截
 * @author jianghao
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Slf4j
public class MasterSlaveAutoRoutingPlugin implements Interceptor {

    private static final String MASTER = "master";

    private static final String SLAVE = "slave";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        SqlSource sqlSource =  ms.getSqlSource();
//        BoundSql boundSql = sqlSource.getBoundSql("sqlSource");
//        String sql = boundSql.getSql();
//        System.out.println(sql);

//        try {
//        切换读写库
//            DynamicDataSourceContextHolder.push(SqlCommandType.SELECT == ms.getSqlCommandType() ? SLAVE : MASTER);
            return invocation.proceed();
//        } finally {
//            DynamicDataSourceContextHolder.clear();
//        }
    }

    @Override
    public Object plugin(Object target) {
        return target instanceof Executor ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {
    }
}