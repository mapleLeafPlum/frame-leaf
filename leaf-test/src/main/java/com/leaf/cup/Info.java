package com.leaf.cup;

import java.util.Properties;

/**
 * @Description:
 * @Date: 2018/4/28 09:03
 * @Auther: nothing
 */
public class Info {

    public static void main(String[] args) {
        Properties p=System.getProperties();//获取当前的系统属性
        p.list(System.out);//将属性列表输出
        System.out.print("CPU个数:");//Runtime.getRuntime()获取当前运行时的实例
        System.out.println(Runtime.getRuntime().availableProcessors());//availableProcessors()获取当前电脑CPU数量
    }
}
