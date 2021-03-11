package com.chiang.java;

import java.util.Collections;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;

/**
 * 一个资源可以设置多个规则，本类设置可以访问资源的黑白名单
 * @author JiangHao
 */
public class AuthorityRuleDemo {

    private static final String RESOURCE_NAME = "ThisResource";

    public static void main(String[] args) {
        System.out.println("========Testing for black list========");
        initBlackRules("appA,appB");
        testFor(RESOURCE_NAME, "appA");
        testFor(RESOURCE_NAME, "appB");
        testFor(RESOURCE_NAME, "appC");
        testFor(RESOURCE_NAME, "appE");

        System.out.println("========Testing for white list========");
        initWhiteRules("appA,appB");
        testFor(RESOURCE_NAME, "appA");
        testFor(RESOURCE_NAME, "appB");
        testFor(RESOURCE_NAME, "appC");
        testFor(RESOURCE_NAME, "appE");
    }

    private static void testFor(/*@NonNull*/ String resource, /*@NonNull*/ String origin) {
        ContextUtil.enter(resource, origin);
        Entry entry = null;
        try {
            entry = SphU.entry(resource);
            System.out.println(String.format("Passed for resource %s, origin is %s", resource, origin));
        } catch (BlockException ex) {
            System.err.println(String.format("Blocked for resource %s, origin is %s", resource, origin));
        } finally {
            if (entry != null) {
                entry.exit();
            }
            ContextUtil.exit();
        }
    }

    /**
     * 设置白名单
     * @param limitApp 可以访问渠道
     */
    private static void initWhiteRules(String limitApp) {
        AuthorityRule rule = new AuthorityRule();
        rule.setResource(RESOURCE_NAME);
        rule.setStrategy(RuleConstant.AUTHORITY_WHITE);
        rule.setLimitApp(limitApp);
        AuthorityRuleManager.loadRules(Collections.singletonList(rule));
    }

    /**
     * 设置黑名单
     * @param limitApp 限制访问的渠道
     */
    private static void initBlackRules(String limitApp) {
        AuthorityRule rule = new AuthorityRule();
        rule.setResource(RESOURCE_NAME);
        rule.setStrategy(RuleConstant.AUTHORITY_BLACK);
        rule.setLimitApp(limitApp);
        AuthorityRuleManager.loadRules(Collections.singletonList(rule));
    }
}