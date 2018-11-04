package com.meiya.nettypackage10;

import com.example.tutorial.AddressBookProtos;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ServerChannelInitilizer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        /*
        * 类似于filter
        * */
        ChannelPipeline pipeline = channel.pipeline();

        /*
        * 编码
        * */
        pipeline.addLast("protobufVarint32LengthFieldPrepender", new ProtobufVarint32LengthFieldPrepender());

        pipeline.addLast("protobufEncoder", new ProtobufEncoder());

        /*
        * 解码
        * 解码器根据消息ByteBuf中的Google Protocol Buffers Base 128 Varints整数长度字段的值动态分割接收到的s
        * */
        pipeline.addLast("protobufVarint32FrameDecoder", new ProtobufVarint32FrameDecoder());
        //将收到的解码ChannelBuffer到 Google Protocol Buffers Message和MessageLite  需和上配合一起使用
        pipeline.addLast("protobufDecoder", new ProtobufDecoder(AddressBookProtos.AddressBook.getDefaultInstance()));

        pipeline.addLast(new ServerHandler());
    }
}
