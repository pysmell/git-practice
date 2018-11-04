package com.meiya.netty权威指南学习.netty.package2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * 客户端，会出现粘包
 */
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {

        int port = 8080;

        String host = "127.0.0.1";

        if (args != null && args.length > 0) {

            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                //默认端口
            }

        }
        new TimeClient().doConnection(port, host);
    }

    private void doConnection(int port, String host) throws InterruptedException {
        //配置NIO客户端线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            //启动客户端辅助类
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new TimeClientHandler());
                }
            });
            //异步连接，调用同步方法，等待连接成功
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            //等待客户端链路关闭
            channelFuture.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }

}
