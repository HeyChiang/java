package com.spring.annotation.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib子类代理工厂（对Dao在内存中动态创建代理）
 * @author Chiang
 */
public class CGLibProxyFactory implements MethodInterceptor {

	private Object target;

	public CGLibProxyFactory(Object target) {
		super();
		this.target = target;
	}

	public Object getProxyInstance(){
		//1.工具类
		Enhancer en = new Enhancer();
		//2.设置父类
		en.setSuperclass(target.getClass());
		//3.设置回调方法,this指的就是本类的interceptor方法
		en.setCallback(this);
		//4.创建代理对象
		return en.create();
	}

	@Override
	public Object intercept(Object arg0, Method method, Object[] arg2, MethodProxy arg3) throws Throwable {
		//执行目标对象的方法
		Object resultValue = method.invoke(target, arg2);
		//---------此处实现代理业务-------------
		System.out.println("我会说中国话");
		return resultValue;
	}
}

