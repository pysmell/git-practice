package com.meiya.nettypackage8;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/*
* netty实现webSocket
* 服务端启动程序
* */
public class WebSocketServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(eventLoopGroup, workerGroup).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new WebSocketChannelInitializer());

        ChannelFuture channelFuture = serverBootstrap.bind(8989).sync();

        channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}





















