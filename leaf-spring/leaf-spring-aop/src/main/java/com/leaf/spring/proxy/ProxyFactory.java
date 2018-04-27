package com.leaf.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    public static LearnSpring getLearnSpring(){
        HandLearSpring learSpring=new HandLearSpring();
        InvocationHandler invocation=new LearnSpringInvocation(learSpring);
        return (LearnSpring)Proxy.newProxyInstance(invocation.getClass().getClassLoader(),
                learSpring.getClass().getInterfaces(),
                invocation);
    }
}
