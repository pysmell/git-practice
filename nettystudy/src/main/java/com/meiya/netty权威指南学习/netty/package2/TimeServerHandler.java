package com.meiya.netty权威指南学习.netty.package2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.Date;

public class TimeServerHandler extends ChannelHandlerAdapter {

    private int counter;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        byte[] req = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(req);

        String body = new String(req, "UTF-8").substring(0, req.length-System.getProperty("line.separator").length());

        System.out.println("The time server receive order: " + body + "; the counter is: " + ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());

        ctx.writeAndFlush(resp);
    }
}












































