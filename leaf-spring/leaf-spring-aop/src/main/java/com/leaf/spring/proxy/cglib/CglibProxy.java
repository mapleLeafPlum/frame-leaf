package com.leaf.spring.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor{

    private Enhancer enhancer=new Enhancer();

    public Object getProxy (Class<?> clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        Object object=enhancer.create();

        return object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println(o.getClass().getName()+"."+method.getName());
        System.out.println(methodProxy.getSignature()+","+methodProxy.getSuperName()+","+getClass());
        Object result= methodProxy.invokeSuper(o,objects);
        System.out.println("over");

        return result;
    }

    public static void main(String[] args) {
        CglibProxy proxy=new CglibProxy();
        People people= (People) proxy.getProxy(People.class);
        //System.out.println(people.getClass().getName());
        people.getName();
    }
}
