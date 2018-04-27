package com.leaf.elasticsearch.model;

import java.util.Arrays;

public class Person {
    public static final String INDEX="customer";
    public static final String TYPE="person";
    public static final String ID="id";

    public static final String ABOUNT="about";
    public static final String FIRST_NAME="first_name";
    public static final String LAST_NAME="last_name";
    public static final String AGE="age";
    public static final String COUNT="count";
    public static final String INTERESTS="interests";

    private String about;
    private String first_name;
    private String last_name;
    private Long age;
    private Long count;
    private String interests[];

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    @Override
    public String toString() {
        return "Person{" +
                "about='" + about + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", age=" + age +
                ", count=" + count +
                ", interests=" + Arrays.toString(interests) +
                '}';
    }
}
