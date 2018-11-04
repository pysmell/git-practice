package com.meiya.netty权威指南学习.netty.package4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务端，DelimiterBasedFrameDecoder使用
 */
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

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, worker).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));

                    socketChannel.pipeline().addLast(new StringDecoder());

                    socketChannel.pipeline().addLast(new EchoServerHandler());
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }

}
