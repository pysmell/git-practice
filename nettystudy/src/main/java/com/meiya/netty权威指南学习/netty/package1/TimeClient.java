package com.meiya.netty权威指南学习.netty.package1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author linqw
 * @date 2018年5月23
 * @version 1.1
 */
public class TimeClient {

    public static void main(String[] args) throws InterruptedException {

        int port = 8080;

        String host = "127.0.0.1";

        if (args != null && args.length > 0) {

            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //默认端口
            }
        }

        new TimeClient().connect(port, host);
    }

    public void connect(int port, String host) throws InterruptedException {
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //客户端辅助启动类
            Bootstrap bootstrap = new Bootstrap();
            //TCP_NODELAY算法，一次发送一个字节，带宽小会导致阻塞,打开此算法会达到一定的字节和一定的时间
            //实现initChannel方法，其作用是当创建NioSocketChannel成功之后,
            //在进行初始化时，将它的ChannelHandler设置到ChannelPipeline中，用于处理网络IO事件
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new TimeClientHandler());
                }
            });
            //等待连接成功，发起异步连接，调用同步方法等待连接成功
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();

            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}







































