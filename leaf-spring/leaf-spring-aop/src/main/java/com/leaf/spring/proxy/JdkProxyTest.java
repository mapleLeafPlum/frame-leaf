package com.leaf.spring.proxy;

public class JdkProxyTest {
    public static void main(String[] args) {
        ProxyFactory.getLearnSpring().howLear();
        System.out.println(ProxyFactory.getLearnSpring().getClass());
        ProxyFactory.getLearnSpring().howLear();
        System.out.println(ProxyFactory.getLearnSpring().getClass());
    }
}
