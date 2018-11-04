package com.meiya.netty权威指南学习.netty.package1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author linqw
 * @date 2018年5月23
 * @version 1.1
 */
public class TimeServer {

    public static void main(String[] args) throws InterruptedException {

        int port = 8080;

        if (args != null && args.length > 0) {

            try {

                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {
                //采用默认值
            }

        }

        new TimeServer().bind(port);
    }

    public void bind(int port) throws InterruptedException {
        //配置服务端的NIO线程组，连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //IO读写
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //用于启动NIO服务端的辅助启动类，降低开发
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChildChannelHandler());

            //绑定端口，同步等待成功,异步操作的通知回调
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            //等待服务端监听端口关闭,等待服务端链路关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            //会释放shutdownGracefully相关联的资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}






































































