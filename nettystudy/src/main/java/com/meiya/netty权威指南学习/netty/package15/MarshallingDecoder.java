package com.meiya.netty权威指南学习.netty.package15;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

public class MarshallingDecoder {

    private final Unmarshaller unmarshaller;

    public MarshallingDecoder() throws IOException {
        this.unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

    protected Object decode(ByteBuf in) throws IOException, ClassNotFoundException {
        //读取第一个4bytes，里面放置的是Object对象的byte长度
        int objectSize = in.readInt();
        ByteBuf bodyByte = in.slice(in.readerIndex(), objectSize);
        //使用byteBuf代理类
        ByteInput byteInput = new ChannelBufferByteInput(bodyByte);
        try {
            //开始解码
            unmarshaller.start(byteInput);
            Object object = unmarshaller.readObject();
            unmarshaller.finish();
            //读完之后，需自行设置读取的位置
            in.readerIndex(in.readerIndex() + objectSize);
            return object;
        } finally {
            unmarshaller.close();
        }
    }
}


































