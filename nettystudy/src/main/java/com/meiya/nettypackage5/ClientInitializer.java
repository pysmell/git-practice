package com.meiya.nettypackage5;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline channelPipeline = channel.pipeline();

        channelPipeline.addLast("lengthFieldBasedFrameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 3, 0, 3));

        channelPipeline.addLast("lengthFieldPrepender", new LengthFieldPrepender(2));

        //StringDecoder字符串的解码器，主要用于处理编码格式
        channelPipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        //StringDecoder字符串的编码器，主要用于处理编码格式
        channelPipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
        //自定义的handler
        channelPipeline.addLast(new MyClientHandler());
    }
}
