package com.meiya.netty权威指南学习.netty.package7;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.unpacker.Unpacker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 顺序序列化多个对象
 */
public class SerializablePerform2 {

    public static void main(String[] args) throws IOException {

        UserInfo userInfo = new UserInfo();

        UserInfo userInfo1 = new UserInfo();

        UserInfo userInfo2 = new UserInfo();

        userInfo.buildUserID(25).buildUserName("linqw");

        userInfo1.buildUserID(24).buildUserName("hll");

        userInfo2.buildUserID(24).buildUserName("lbf");

        MessagePack messagePack = new MessagePack();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Packer packer = messagePack.createPacker(byteArrayOutputStream);

        packer.write(userInfo);

        packer.write(userInfo1);

        packer.write(userInfo2);

        byte[] bytes = byteArrayOutputStream.toByteArray();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        Unpacker unpacker = messagePack.createUnpacker(byteArrayInputStream);

        System.out.println(unpacker.read(UserInfo.class));

        System.out.println(unpacker.read(UserInfo.class));

        System.out.println(unpacker.read(UserInfo.class));
    }

}
/**
 * MessagePack特点
 * 编解码高效，性能高
 * 序列化后的码流小
 * 支持跨语言
 */
