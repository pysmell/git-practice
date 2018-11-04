package com.meiya.netty权威指南学习.netty.package2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

public class ChildChannelHandler extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new TimeServerHandler());
    }
}
