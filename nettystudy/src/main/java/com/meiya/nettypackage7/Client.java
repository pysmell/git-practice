package com.meiya.nettypackage7;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ClientInitializer());

        try {
            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            channel.writeAndFlush(bufferedReader.readLine() + "\n");
        } finally {
            eventLoopGroup.shutdownGracefully();  //优雅的关闭
        }


    }
}
