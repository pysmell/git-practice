package com.meiya.nettypackage11;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientTest {

    public static void main(String[] args) throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientChannelInitilizer());

            ChannelFuture channelFuture = bootstrap.connect("localhost", 8888).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
