package com.meiya.netty权威指南学习.netty.package15;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;

import java.io.IOException;

public class ChannelBufferByteInput implements ByteInput {

    private final ByteBuf byteBuf;

    public ChannelBufferByteInput(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    @Override
    public int read() throws IOException {

        //判断是否有数据可读，转换为int类型返回
        if (byteBuf.isReadable()) {
            return byteBuf.readByte() & 0xff;
        }
        return -1;
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return read(bytes, 0, bytes.length);
    }

    @Override
    public int read(byte[] bytes, int dstIndex, int length) throws IOException {

        int available = available();

        if (available == 0) {
            return -1;
        }

        length = Math.min(length, available);

        byteBuf.readBytes(bytes, dstIndex, length);

        return 0;
    }

    @Override
    public int available() throws IOException {
        return byteBuf.readableBytes();
    }

    @Override
    public long skip(long l) throws IOException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}





















