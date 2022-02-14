package com.spring.annotation.proxy.cglib;

//测试类
public class TestMain {
	public static void main(String[] args) {
		HumanImpl human = new HumanImpl();
		CGLibProxyFactory factory = new CGLibProxyFactory(human);
		HumanImpl humanProxy = (HumanImpl) factory.getProxyInstance();
		humanProxy.say();
	}
}
