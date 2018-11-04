package com.meiya.nettypackage8;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    /*@Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

        Channel channel = channelHandlerContext.channel();

        System.out.println(channel.remoteAddress() + "：" + textWebSocketFrame.text());

        channel.writeAndFlush(new TextWebSocketFrame("来自服务端" + LocalDateTime.now()));
    }*/

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("用户：" + ctx.channel().id().asLongText() + " 上线");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("用户：" + ctx.channel().id().asLongText() + " 下线");
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

    }
}






























