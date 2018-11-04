package com.meiya.netty权威指南学习.netty.package6;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * java序列化
 */
public class UserInfo implements Serializable {
    
    /**
     * 默认序列号
     */
    private static final long serialVersionUID = 1L;

    private String userName;

    private int userID;

    public UserInfo buildUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public UserInfo buildUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public byte[] codeC() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        byteBuffer.putInt(value.length);
        byteBuffer.put(value);
        byteBuffer.putInt(this.userID);
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return bytes;
    }

    public byte[] codeC(ByteBuffer byteBuffer) {
        byteBuffer.clear();
        byte[] value = this.userName.getBytes();
        byteBuffer.putInt(value.length);
        byteBuffer.put(value);
        byteBuffer.putInt(this.userID);
        byteBuffer.flip();
        value = null;
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return bytes;
    }

}
/**
 * java序列化缺点
 * 1、无法跨语言，是java序列化最致命的问题，对于跨进程的服务调用，服务提供者可能会使用C++
 * 或者其他语言开发，当我们需要和异构语言进程交互时，java序列化难以胜任
 */








































