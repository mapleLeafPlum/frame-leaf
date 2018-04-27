package com.leaf.kafka.producer;

public class ProducerTest {
    public static void main(String[] args) {
        System.out.println("start....");
        new Thread(new Producer("spark",false)).start();
    }
}
