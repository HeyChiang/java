package com.spring.annotation.proxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * 创建动态代理工厂
 * @author Chiang
 */
public class ProxyFactory{

	private Object target;

	public ProxyFactory(Object target){
		this.target = target;
	}

	/**
	 * 生成代理对象
	 */
	public Object getProxyInstance(){
	 /**
	  * Proxy.newProxyInstance(ClassLoader loader, Class<?>[] interfaces. InvocationHandler h)
      * loader 指定当前目标对象使用 类的加载器
      * interfaces 目标对象实现的接口类型
      * h  事件处理器
	  */
	  return Proxy.newProxyInstance(
		target.getClass().getClassLoader(),
		target.getClass().getInterfaces(),
		new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object result = method.invoke(target, args);
				//--------此处实现代理业务--------------
				System.out.println("我会说中文");
				//--------返回代理对象--------------
				return result;
			}
		});
	}
}

