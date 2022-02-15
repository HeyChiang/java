package com.spring.annotation.proxy.proxy;

import com.spring.annotation.proxy.cglib.HumanImpl;
import com.spring.annotation.proxy.cglib.IHuman;

/**
 * @author JiangHao at 2022/2/14 9:04
 */
public class TestMain {
    public static void main(String[] args) {
        HumanImpl human = new HumanImpl();
        ProxyFactory factory = new ProxyFactory(human);
        IHuman humanProxy = (IHuman) factory.getProxyInstance();
        humanProxy.say();
    }
}
