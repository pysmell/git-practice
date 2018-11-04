package com.meiya.netty权威指南学习.netty.package15;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.awt.*;

public class LoginAuthReqHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }

    private NettyMessage buildLoginReq() {
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        return nettyMessage;
    }
















}
