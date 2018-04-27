package com.leaf.elasticsearch.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Date: 2018/4/26 18:25
 * @Auther: nothing
 */
public class Test {
    public static void main(String[] args) {
        Test test=new Test();
        test.test();
    }

    public void test(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Before Java8, too much code for too little to do");
            }
        }).start();

        new Thread(()->{System.out.println("lambda 1"); }).start();
        new Thread(()->System.out.println("lambda 2")).start();

        PersonFactor factor=new PersonFactor();
        factor.getName((name)->{ System.out.println(name); });

        factor.getName(new IPerson() {
            @Override
            public void getName(String nickName) {
                System.out.println(nickName);
            }
        });

        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
      /*  for (Object feature : features) {
            System.out.println(feature);
        }*/

       // features.forEach(n -> System.out.println(n));
        features.forEach(x->System.out.println(x));


        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        // 看起来像C++的作用域解析运算符
        features.forEach(System.out::println);

    }








}
