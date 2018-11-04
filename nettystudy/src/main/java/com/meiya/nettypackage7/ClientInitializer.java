package com.meiya.nettypackage7;


import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ClientInitializer extends ChannelInitializer {


    @Override
    protected void initChannel(Channel channel) throws Exception {

        ChannelPipeline channelPipeline = channel.pipeline();

        channelPipeline.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));

        channelPipeline.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
    }
}
