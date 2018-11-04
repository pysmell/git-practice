package com.meiya.netty权威指南学习.netty.package10;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import netty.codec.protobuf.SubscribeRespProto;

public class SubReqClient {

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
        new SubReqClient().doConnect(port, host);
    }

    private void doConnect(int port, String host) throws InterruptedException {

        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    //处理半包
                    socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    socketChannel.pipeline().addLast(new ProtobufDecoder(SubscribeRespProto.SubscribeResp.getDefaultInstance()));
                    socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    socketChannel.pipeline().addLast(new ProtobufEncoder());
                    socketChannel.pipeline().addLast(new SubReqClientHandler());
                }
            });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
