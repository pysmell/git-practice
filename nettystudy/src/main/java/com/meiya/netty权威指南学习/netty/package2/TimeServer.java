package com.meiya.netty权威指南学习.netty.package2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author linqw
 * 服务端，会出现粘包
 */
public class TimeServer {

    public static void main(String[] args) throws InterruptedException {

        int port = 8080;

        if (args != null && args.length >0) {

            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //采用默认方式
            }

        }
        new TimeServer().doAccept(port);
    }

    private void doAccept(int port) throws InterruptedException {

        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup worker = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup, worker).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChildChannelHandler());

            //同步等待绑定端口成功
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {

            bossGroup.shutdownGracefully();

            worker.shutdownGracefully();
        }
    }

}
/**
 * 出现TCP粘包/分包的原因
 * 1、应用程序写入的字节大小大于套接字发生缓冲区的大小，会发生拆包现象，而应用程序写入数据小于套接字
 * 缓冲区大小，网卡将应用多次写入的数据发生到网络上，这将会发生粘包现象
 * 2、进行mss（最大报文长度）大小的TCP分段，当TCP报文长度-TCP头部长度>MSS的时候将发生拆包
 * 3、以太网帧的payload（净荷）大于MTU(1500字节)进行ip分片
 */
/**
 * 解决方案
 * 1、消息定长，例如每个报文的大小为固定长度200字节，如果不够，空位补空格
 * 2、在包尾增加回车换行符进行分割，例如ftp协议
 * 3、将消息分为消息头和消息体，消息头中包含表示消息总长度(或者消息体长度)的字段
 * 通常设计思路为消息头的第一个字段使用int32来表示消息的总长度
 * 4、更复杂的应用层协议
 */
































