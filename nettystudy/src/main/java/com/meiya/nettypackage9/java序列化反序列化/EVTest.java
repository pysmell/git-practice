package com.meiya.nettypackage9.java序列化反序列化;

import java.io.*;

public class EVTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialzeUser();
        System.out.println(DeserializeUser ());
    }

    private static void SerialzeUser() throws IOException {
        Person person = new Person("林启温", 25);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("G:person.txt")));
        objectOutputStream.writeObject(person);
    }

    private static Person DeserializeUser () throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("G:person.txt")));
        Person person =  (Person) objectInputStream.readObject();
        return person;
    }
}
