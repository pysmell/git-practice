package com.meiya.nettypackage9.java序列化反序列化;

import java.io.Serializable;

/*
* java对象序列化机制
* 是将java对象转化为二进制的字节流，然后保存到磁盘中或者在网络上。这就是序列化对象，反序列化就是将对象的二进制字节流恢复成原来的对象
* 只有对象的类名和属性能被序列化(包括基本类型，数组，对其他对象的引用)不包括方法，static属性(静态属性)，transient属性(瞬时属性)将不会被序列化
* */
public class User implements Serializable {

    /*
    * 如果这个不写会自动根据类名、属性、方法等生成，如果本类有所改动，自动生成每次都会改变，会导致反序列化失败
    *
    * */
    private static final long serialVersionUID = 1054534944909885290L;
    
    private int id;

    private String name;

    public User() {
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}




















































