package com.spring.annotation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BuyAspect {

    /**
     * execution 匹配在方法执行时触发，例：execution(方法修饰符_可选 返回类型 方法名 参数 异常模式_可选)
     * \@annotation() 匹配标注了指定注解的目标类方法，例：@Transactional
     * args() 匹配使用了指定参数的方法，例：args(String) ，表示有且仅有一个String型参数的方法
     * \@args() 匹配使用了指定参数的方法，如 @args(org.springframework.stereotype.Service) 表示有且仅有一个标注了@Service的类参数的方法
     * within() 匹配类名指定切点，如 with(com.example.Demo) 表示Demo的所有方法
     * \@within() 匹配标注了指定注解的类及其所有子类 如@within(org.springframework.stereotype.Service)
     * \@target() 所有标注了指定注解的类 如@target(org.springframework.stereotype.Service)表示所有标注了@Service的类的所有方法
     * target() 通过类名指定，同时包含所有子类 如 target(com.example.Demo)
     * this() 大部分时候和target()相同，区别是this是在运行时生成代理类后，才判断代理类与指定的对象类型是否匹配
     */
    @Pointcut("execution(* com.spring.annotation.aop.IBuy.buy(..))")
    public void inPointCut(){

    }

    /**
     * execution 代表在方法执行时触发
     */
    @Before("execution(* com.spring.annotation.aop.IBuy.buy(..))")
    public void pointBefore(){
        System.out.println("大家都喜欢去逛街");
    }

    /**
     * 在方法执行后调用，不管是否异常。
     *
     * within：指定某类的所有方法执行后被调用，必须指定具体的类，不能是接口。
     * @within:匹配标注了指定注解的类及其所有子类
     */
    @After("within(com.spring.annotation.aop.Boy)")
    public void pointAfter() {
        System.out.println("pointAfter ...");
    }

    /**
     * 在方法执行后调用，没有异常的情况下
     */
    @AfterReturning("execution(* com.spring.annotation.aop.IBuy.buy(..))")
    public void afterReturning() {
        System.out.println("AfterReturning ...");
    }

    /**
     * 将目标方法围绕起来
     */
    @Around("execution(* com.spring.annotation.aop.IBuy.buy(..))")
    public Object xxx(ProceedingJoinPoint pj) throws Throwable {

        System.out.println("Around aaa ...");
        Object proceed = pj.proceed();
        System.out.println("Around bbb ...");

        return proceed;
    }

}