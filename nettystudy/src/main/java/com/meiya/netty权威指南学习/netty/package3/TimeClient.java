package com.meiya.netty权威指南学习.netty.package3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 客户端
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
                    //LineBasedFrameDecoder的工作原理是它依次遍历ByteBuf中的可读字节，
                    //判断看是否有"\n"或者"\r\n",如果有，就以此位置为结束位置，从可读索引到结束位置区间的字节就组成了一行，
                    //支持携带结束符或者不携带结束符两种解码方式，同时支持配置单行的最大长度，
                    //如果连续读取到最大长度后仍然没有发现换行符，就会抛出异常，忽略掉之前读到的异常码流
                    socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                    //将接收到的对象转换成字符串，然后继续调用后面的handler
                    socketChannel.pipeline().addLast(new StringDecoder());
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
