package com.meiya.netty权威指南学习.netty.package15;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.Marshaller;

import java.io.IOException;

/**
 * 使用marshall对Object进行编码，并且写入bytebuf
 */
public class MarshallingEncoder {

    private static final byte[] LENGTH_PLACEHOLDER = new byte[4];

    private Marshaller marshaller;

    public MarshallingEncoder() throws IOException {
        marshaller = MarshallingCodecFactory.buildMarshalling();
    }

    public void encode(Object msg, ByteBuf out) throws IOException {
        try {
            //获取写入位置
            int lengthPos = out.writerIndex();
            //先写入4个bytes，用于记录Object对象编码后长度
            out.writeBytes(LENGTH_PLACEHOLDER);
            ChannelBufferByteOutput channelBufferByteOutput = new ChannelBufferByteOutput(out);
            marshaller.start(channelBufferByteOutput);
            marshaller.writeObject(msg);
            //结束编码
            marshaller.finish();
            //设置对象长度
            out.setInt(lengthPos, out.writerIndex()  - 4);
        } finally {
            marshaller.close();
        }
    }
}



























