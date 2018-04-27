package com.leaf.spring.socket;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String message;
    private List<String> userList=new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }
}
