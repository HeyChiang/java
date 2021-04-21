package com.chiang.java;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 流量控制
 * @author jianghao
 */
public class FlowRuleDemo {
    public static void main(String[] args) throws InterruptedException {
        // 配置规则.
        initFlowRules();

        while (true) {
            Thread.sleep(100);

            // 开始进入名字叫HelloWord
            try (Entry entry = SphU.entry("HelloWorld")) {
                // 被保护的逻辑
                System.out.println("我是资源");
            } catch (BlockException ex) {
                // 处理被流控的逻辑
                System.out.println("blocked!");
            }
        }
    }

    /**
     * 定义资源访问的限制
     */
    private static void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 定义需要被控制的资源名词
        rule.setResource("HelloWorld");
        // 按照每秒的请求数量来限制。也可以用 FLOW_GRADE_THREAD 限制并发数
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 每秒执行多少次开始限制
        rule.setCount(5);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
