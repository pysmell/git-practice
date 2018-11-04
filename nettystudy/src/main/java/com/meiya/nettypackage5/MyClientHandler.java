package com.meiya.nettypackage5;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class MyClientHandler extends SimpleChannelInboundHandler<String> {


  /*  @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println(channelHandlerContext.channel().remoteAddress());
        System.out.println(s);
        channelHandlerContext.writeAndFlush("to EchoServer:草泥马");
    }*/

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "...........");
        ctx.channel().writeAndFlush("来自于客户端的问候！");
        System.out.println("client channel Active...");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "...........");
        System.out.println("client hanlder added...");
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(System.currentTimeMillis() + "...........");
        System.out.println("client channel register...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel inactive...");
    }

  /*  @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("client channel unregister...");
    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {

    }
}
