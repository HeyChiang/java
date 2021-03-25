package com.chiang.sentinel.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置此类支持注解 @SentinelResource
 * @author jianghao
 */
@Configuration
public class SentinelAspectConfiguration {

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        initFlowRules();

        return new SentinelResourceAspect();
    }

    /**
     * 定义资源访问的限制
     */
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 定义需要被控制的资源名词
        rule.setResource("hello");
        // 按照每秒的请求数量来限制。也可以按照线程数来
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 每秒执行多少次开始限制
        rule.setCount(1);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}