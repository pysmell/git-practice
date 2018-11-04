package com.meiya.netty权威指南学习.netty.package15;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    MarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf byteBuf = (ByteBuf) super.decode(ctx, in);
        //证明读取到半包，返回不做处理
        if (byteBuf == null) {
            return null;
        }
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(byteBuf.readInt());
        header.setLength(byteBuf.readInt());
        header.setSessionID(byteBuf.readLong());
        header.setType(byteBuf.readByte());
        header.setPriority(byteBuf.readByte());

        int size = byteBuf.readInt();

        if (size > 0) {
            Map<String, Object> attch = new HashMap<>(size);
            int keySize = 0;
            byte[] keyArray = null;
            String key = null;
            for (int i = 0; i < size; i++) {
                keySize = byteBuf.readInt();
                keyArray = new byte[keySize];
                byteBuf.readBytes(keyArray);
                key = new String(keyArray, "UTF-8");
                attch.put(key, marshallingDecoder.decode(byteBuf));
            }
            keyArray = null;
            key = null;
            header.setAttachment(attch);
        }
        message.setHeader(header);
        return message;
    }
}













































