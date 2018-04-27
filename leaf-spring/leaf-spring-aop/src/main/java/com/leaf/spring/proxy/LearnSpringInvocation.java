package com.leaf.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LearnSpringInvocation implements InvocationHandler {

    private Object target;

    public LearnSpringInvocation(Object target){
        this.target=target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("befor....");
        Object object= method.invoke(target,args);
        System.out.println("method:...."+method.getName());
        System.out.println("after....");
        return object;


    }
}
