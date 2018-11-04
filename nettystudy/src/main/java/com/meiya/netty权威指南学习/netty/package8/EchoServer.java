package com.meiya.netty权威指南学习.netty.package8;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class EchoServer {

    public static void main(String[] args) throws InterruptedException {

        int port = 8080;

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //采用默认端口
            }
        }
        new EchoServer().doAccept(port);
    }

    private void doAccept(int port) throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(eventLoopGroup, worker).channel(NioServerSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0, 2, 0, 2));
                    socketChannel.pipeline().addLast(new MsgpackDecoder());
                    socketChannel.pipeline().addLast(new LengthFieldPrepender(2));
                    socketChannel.pipeline().addLast(new MsgpackEncoder());
                    socketChannel.pipeline().addLast(new EchoServerHandler());
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }

}
