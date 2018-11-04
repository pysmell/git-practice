package com.meiya.nettypackage4;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/*
* 通道初始化程序
* 通道的初始化程序主要视为workerGroup添加各种Handler
* */
public class TestChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline channelPipeline = channel.pipeline();
        /*
        * Handler就相当于Servlet中的过滤器，请求和响应都会走Handler
        * HttpServerCodec：http的编码器，用于http请求和响应
        * */
        channelPipeline.addLast("httpServerCodec", new HttpServerCodec());
        channelPipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}
