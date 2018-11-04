package com.meiya.nettypackage4;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/*
* Netty步骤
* 服务器启动
* 服务器主通道监听
* 服务器子通道开通
* 客户端启动
* 客户端主通道监听
* 客户端子通道开通
* */
public class ServerTest {

    public static void main(String[] args) throws InterruptedException {

        /*
        * eventLoopGroup，父类的事件循环组只是负责连接，获取到连接后交给workergroup子的事件循环组
        * 参数的获取，业务的处理等工作均是由workergroup这个自实际循环来完成，一个事件循环组一样可以完成所有的工作，
        * 但是netty推荐的方式是使用两个事件循环组
        *
        * */
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(); //创建父事件循环组

        EventLoopGroup workerGroup = new NioEventLoopGroup(); //创建子类的事件循环组

        try {
            //创建启动服务器的对象
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(eventLoopGroup, workerGroup).channel(NioServerSocketChannel.class) //eventLoopGroup通道，只是负责连接
            .childHandler(new TestChannelInitializer());  //workerGroup的处理器

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}








































































