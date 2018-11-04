package com.meiya.netty权威指南学习.netty.package3;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class ChildChannelHandler  extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //将粘包的字节数组，根据换行符，分割成多行
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));

        //反序列化，从网络上的字节数组还原成原始对象
        socketChannel.pipeline().addLast(new StringDecoder());

        socketChannel.pipeline().addLast(new TimeServerHandler());
    }
}
