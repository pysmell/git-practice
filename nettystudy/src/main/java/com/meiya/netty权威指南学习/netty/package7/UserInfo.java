package com.meiya.netty权威指南学习.netty.package7;

import org.msgpack.annotation.Message;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * messagePack序列化,是一个高效的二进制序列化框架，像json一样支持不同语言间的
 * 数据交换，但是它的性能更快，序列化之后的码流也更小,比protobuf更快
 */
@Message
public class UserInfo{

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

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", userID=" + userID +
                '}';
    }
}








































