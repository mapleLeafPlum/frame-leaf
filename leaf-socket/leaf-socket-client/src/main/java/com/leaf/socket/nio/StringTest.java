package com.leaf.socket.nio;

import java.util.ArrayList;
import java.util.List;

public class StringTest {
    public static void main(String[] args) {
        String name="123 4567";
        //name= String.join("-","456","123");

        List<String> ins=new ArrayList<String>();
        ins.add("10");
        ins.add("20");
        ins.add("30");

        System.out.println(String.join("-",ins));
        System.out.println(name.matches("123"));
        System.out.println(name.contains("123"));

    }

}
