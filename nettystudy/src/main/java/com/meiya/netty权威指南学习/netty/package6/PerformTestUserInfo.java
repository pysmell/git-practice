package com.meiya.netty权威指南学习.netty.package6;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * 编码性能测试
 */
public class PerformTestUserInfo {

    public static void main(String[] args) throws IOException {

        UserInfo userInfo = new UserInfo();

        userInfo.buildUserID(100).buildUserName("welcome to Netty");

        int loop = 100000;

        ByteArrayOutputStream byteArrayOutputStream = null;

        ObjectOutputStream objectOutputStream = null;

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < loop; i++) {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(userInfo);
            objectOutputStream.flush();
            objectOutputStream.close();
            byte[] b = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("The jdk serializable cost time is:" + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        for (int i = 0; i < loop; i++) {

            userInfo.codeC(byteBuffer);

        }

        endTime = System.currentTimeMillis();

        System.out.println("The byte array serializable cost time is : " + (endTime - startTime) + "ms");
    }

}
