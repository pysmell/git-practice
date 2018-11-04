package com.meiya.nettypackage10;

import com.example.tutorial.AddressBookProtos;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ClientChannelInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {

        ChannelPipeline channelPipeline = channel.pipeline();

        /*
        编码
        * */
        channelPipeline.addLast("protobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender());
        channelPipeline.addLast("protobufEncoder", new ProtobufEncoder());
        /*
        * 解码
        * */
        channelPipeline.addLast("protobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder());
        channelPipeline.addLast("protobufDecoder", new ProtobufDecoder(AddressBookProtos.AddressBook.getDefaultInstance()));

        channelPipeline.addLast(new ClientHandler());
    }
}
