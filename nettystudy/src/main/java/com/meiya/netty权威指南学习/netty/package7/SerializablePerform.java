package com.meiya.netty权威指南学习.netty.package7;

import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * 序列化单个对象
 */
public class SerializablePerform {

    public static void main(String[] args) throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.buildUserID(25).buildUserName("linqw");
        MessagePack messagePack = new MessagePack();
        //序列化
        byte[] bytes = messagePack.write(userInfo);
        System.out.println("byte array's length is:" + bytes.length);
        //反序列化
        UserInfo userInfo1 = messagePack.read(bytes, UserInfo.class);
        System.out.println(userInfo1);
    }

}
