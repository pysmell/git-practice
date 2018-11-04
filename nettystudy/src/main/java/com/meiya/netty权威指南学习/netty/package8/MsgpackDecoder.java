package com.meiya.netty权威指南学习.netty.package8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * 没有考虑粘包和拆包
 * 解码器
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        int length = byteBuf.readableBytes();

        byte[] bytes = new byte[length];

        byteBuf.getBytes(byteBuf.readerIndex(), bytes, 0, length);

        MessagePack messagePack = new MessagePack();

        list.add(messagePack.read(bytes));

    }
}
