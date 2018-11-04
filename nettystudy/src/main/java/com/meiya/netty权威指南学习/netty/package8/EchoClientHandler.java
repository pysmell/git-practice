package com.meiya.netty权威指南学习.netty.package8;

import com.meiya.nettypackage9.java序列化反序列化.User;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class EchoClientHandler extends ChannelHandlerAdapter {

    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        UserInfo[] userInfos = userInfo();

        for (int i = 0; i < userInfos.length; i++) {
            ctx.writeAndFlush(userInfos[i]);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack message :" + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private UserInfo[] userInfo() {

        UserInfo[] userInfos = new UserInfo[sendNumber];

        UserInfo userInfo = null;

        for (int i = 0; i < sendNumber; i++) {
            userInfo = new UserInfo();
            userInfo.setAge(i);
            userInfo.setUserName("ABCDEFG --->" + i);
            userInfos[i] = userInfo;
        }

        return userInfos;
    }
}
