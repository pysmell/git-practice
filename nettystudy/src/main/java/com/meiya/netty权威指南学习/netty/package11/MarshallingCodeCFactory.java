package com.meiya.netty权威指南学习.netty.package11;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

/**
 * 编解码工厂类
 * netty的Marshalling编解码器支持半包和粘包的处理，Marshalling也是异步非阻塞的
 */
public class MarshallingCodeCFactory {

    /**
     * 创建JBOSS Marshalling编码器MarshallingEncoder
     */
    public static MarshallingDecoder buildMarshallingDecoder() {
        //通过Marshalling工具类的getProvidedMarshallerFactory静态方法获取MarshallerFactory实例，参数"serial"表示创建的是java序列化工厂对象
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
        //有两个参数分别是UnmarshallerProvider和单个消息序列化后的最大长度
        MarshallingDecoder decoder = new MarshallingDecoder(provider, 1024);
        return decoder;
    }

    public static MarshallingEncoder buildMarshallingEncoder() {
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        MarshallingEncoder encoder = new MarshallingEncoder(provider);
        return encoder;
    }
}
