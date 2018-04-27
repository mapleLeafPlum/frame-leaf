package com.leaf.spring.proxy;

public class HandLearSpring implements LearnSpring {
    @Override
    public void howLear() {
        System.out.println("watch video,book");
    }

    @Override
    public void learTime() {
        System.out.println("1 hour every day");
    }

    @Override
    public void tryLearn() {
        System.out.println("so hard i want give up");
    }
}
