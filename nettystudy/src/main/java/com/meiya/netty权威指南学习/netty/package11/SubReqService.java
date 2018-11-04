package com.meiya.netty权威指南学习.netty.package11;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 服务端
 */
public class SubReqService {

    public static void main(String[] args) throws InterruptedException {

        int port = 8080;

        if (args != null && args.length > 0) {

            try {

                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {
                //采用默认端口
            }

        }

        new SubReqService().doAccept(port);
    }

    private void doAccept(int port) throws InterruptedException {
        //连接线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //IO处理线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {

                    socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                    socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                    socketChannel.pipeline().addLast(new SubReqServiceHandler());
                }
            });

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
