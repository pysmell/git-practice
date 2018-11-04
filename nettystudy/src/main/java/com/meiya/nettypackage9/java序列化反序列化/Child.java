package com.meiya.nettypackage9.java序列化反序列化;

import java.io.Serializable;

public class Child extends Parent implements Serializable {

    String name;

    int age;

    public Child(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Child(int money, String home, String name, String name1, int age) {
        super(money, home, name);
        this.name = name1;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", money=" + money +
                ", home='" + home + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    private void writeObject (Object obj) {
        System.out.println("writeObject被调用");
    }
}
