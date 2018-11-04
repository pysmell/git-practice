package com.meiya.nettypackage5;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
@ChannelHandler.Sharable
public class MyHandler extends SimpleChannelInboundHandler<String> {

  /*  @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress() + ":" + s);
        channelHandlerContext.channel().writeAndFlush("from server:牛逼");
    }*/
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server handler added**********");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server channel register****");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "********");
        System.out.println("server channel actieve****");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }
}
