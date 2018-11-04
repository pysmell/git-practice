package com.meiya.netty权威指南学习.netty.package6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * java序列号后的码流太大
 */
public class TestUserInfo {

    public static void main(String[] args) throws IOException {

        UserInfo userInfo = new UserInfo();

        userInfo.buildUserID(100).buildUserName("welcome to netty");

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        objectOutputStream.writeObject(userInfo);

        objectOutputStream.flush();

        objectOutputStream.close();

        byte[] bytes = byteArrayOutputStream.toByteArray();

        System.out.println("The jdk serializable length is: " + bytes.length);

        byteArrayOutputStream.close();

        System.out.println("----------------------------------------");

        System.out.println("The byte array serializable length is :" + userInfo.codeC().length);
    }

}
/**
 * 编码解码框架优劣性
 * 1、是否支持跨语言，支持的语言种类是否丰富
 * 2、编码后的码流大小
 * 3、编解码性能
 * 4、类库是否小巧，api使用是否方便
 * 5、使用者需要手工开发的工作量和难度
 */
/**
 * 编码后的字节数组越大，存储的时候就越占空间，存储的硬件成本就越高，并且在网络传输时更占带宽
 * 导致系统的吞吐量降低
 */











































