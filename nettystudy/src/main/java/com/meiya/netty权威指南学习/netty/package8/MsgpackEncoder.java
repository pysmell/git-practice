package com.meiya.netty权威指南学习.netty.package8;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * 编码器
 */
public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

        MessagePack messagePack = new MessagePack();

        byte[] bytes = messagePack.write(o);

        byteBuf.writeBytes(bytes);

    }
}
