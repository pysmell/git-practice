package com.meiya.netty权威指南学习.netty.package4;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 客户端
 */
public class EchoClient {

    public static void main(String[] args) throws InterruptedException {

        int port = 8080;

        String host = "127.0.0.1";

        if (args != null && args.length > 0) {
            try {

                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {
                //采用默认端口
            }
        }
        new EchoClient().doConnect(port, host);
    }

    private void doConnect(int port, String host) throws InterruptedException {

        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(worker).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new LoggingHandler(LogLevel.INFO)).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

                    socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));

                    socketChannel.pipeline().addLast(new StringDecoder());

                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            worker.shutdownGracefully();
        }

    }
}
