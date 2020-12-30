package com.spring.annotation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BuyAspectJ {

    /**
     * execution 代表在方法执行时触发
     */
    @Before("execution(* com.spring.annotation.aop.IBuy.buy(..))")
    public void pointBefore(){
        System.out.println("大家都喜欢去逛街");
    }

    /**
     * 在方法执行后调用，不管是否异常
     */
    @After("execution(* com.spring.annotation.aop.IBuy.buy(..))")
    public void pointAfter() {
        System.out.println("After ...");
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