package com.meiya.netty权威指南学习.netty.package1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TimeClientHandler.class);

    private final ByteBuf byteBuf;

    public TimeClientHandler() {

        byte[] req = "QUERY TIME ORDER".getBytes();
        byteBuf = Unpooled.buffer(req.length);
        byteBuf.writeBytes(req);
    }

    //当发生异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();

    }

    //当客户端和服务端TCP链路建立成功之后，会调用此方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(byteBuf);
    }

    //当服务端返回应答消息
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf byteBuf = (ByteBuf) msg;

        byte[] bytes = new byte[byteBuf.readableBytes()];

        byteBuf.readBytes(bytes);

        String body = new String(bytes, "UTF-8");

        System.out.println("Now is:" + body);
    }
}














































