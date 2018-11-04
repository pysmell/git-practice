package com.meiya.nettypackage6;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ChartServer {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(eventLoopGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChartServerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(8877).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
