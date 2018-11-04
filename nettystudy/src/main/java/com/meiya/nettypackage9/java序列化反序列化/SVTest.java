package com.meiya.nettypackage9.java序列化反序列化;

import java.io.*;

public class SVTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialzeUser();
        User user = DeserializeUser();
        System.out.println(user);
    }

    /*
    * 序列化对象
    * */
    private static void SerialzeUser() throws IOException {
        User user = new User(1, "小明");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("d:/user.txt")));
        objectOutputStream.writeObject(user);
        System.out.println("序列化成功");
        objectOutputStream.close();
    }

    private static User DeserializeUser () throws IOException, ClassNotFoundException {

        ObjectInputStream objectOutputStream = new ObjectInputStream(new FileInputStream(new File("d:/user.txt")));
        //反序列化对象
        User user = (User) objectOutputStream.readObject();

        System.out.println("反序列化成功");

        objectOutputStream.close();

        return user;
    }
}
