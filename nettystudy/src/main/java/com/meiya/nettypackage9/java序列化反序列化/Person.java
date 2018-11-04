package com.meiya.nettypackage9.java序列化反序列化;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Person implements Externalizable {

    private static final long serialVersionUID = -634790450756812062L;

    private String name;

    private int age;

    /*
    * Externalizable 需要无参的构造方法
    * */
    public Person() {
        System.out.println("无参构造方法");
    }

    public Person(String name, int age) {
        System.out.println("有参构造方法");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

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
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal" + "被调用");
        out.writeObject(this.name);
        out.writeObject(this.age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal" + "被调用");
        this.name = (String) in.readObject();
        this.age = (int) in.readObject();
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
