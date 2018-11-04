package com.meiya.netty权威指南学习.netty.package8;

import org.msgpack.annotation.Message;

@Message
public class UserInfo {

    private int age;

    private String userName;

    public UserInfo() {
    }

    public UserInfo(int age, String userName) {
        this.age = age;
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", userName='" + userName + '\'' +
                '}';
    }
}



























































