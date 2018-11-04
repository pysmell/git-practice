package com.meiya.nettypackage6;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline channelPipeline = channel.pipeline();

        channelPipeline.addLast("delimiterBasedFrameDecoder", new DelimiterBasedFrameDecoder(4096 , Delimiters.lineDelimiter()));

        channelPipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));

        channelPipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));

        channelPipeline.addLast(new ClientHandler());
    }
}

































