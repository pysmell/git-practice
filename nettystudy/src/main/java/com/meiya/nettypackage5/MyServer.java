package com.meiya.nettypackage5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/*
* 服务端启动程序
* */
public class MyServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(eventLoopGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new MyInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}













































