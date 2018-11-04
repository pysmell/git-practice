package com.meiya.netty权威指南学习.netty.package8;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class EchoClient {

    public static void main(String[] args) throws InterruptedException {

        String host = "127.0.0.1";

        int port = 8080;

        int sendNumber = 100;

        if (args != null && args.length > 0) {

            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                //采用默认端口
            }

        }
        new EchoClient().doConnect(port, host, sendNumber);
    }

    private void doConnect(int port, String host, int sendNumber) throws InterruptedException {

        host = (host==null?"127.0.0.1":host);

        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(worker).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,0, 2, 0, 2));
                    socketChannel.pipeline().addLast(new MsgpackDecoder());
                    socketChannel.pipeline().addLast(new LengthFieldPrepender(2));
                    socketChannel.pipeline().addLast(new MsgpackEncoder());
                    socketChannel.pipeline().addLast(new EchoClientHandler(sendNumber));
                }
            });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            worker.shutdownGracefully();
        }

    }

}
