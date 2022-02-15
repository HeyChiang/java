package com.spring.annotation.proxy.cglib;

//HumanImpl.java
public class HumanImpl implements IHuman{
	@Override
	public void say() {
		System.out.println("我是中国人");
	}
}
