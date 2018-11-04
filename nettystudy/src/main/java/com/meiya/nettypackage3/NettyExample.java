package com.meiya.nettypackage3;
/*
* netty源码分析
* org.jboss.netty.bootstrap
* Bootstrap：ChannelFactory,ChannelPipeline,ChannelPipelineFactory
*       初始化channel的辅助类
*       为具体的子类提供公共数据结构
* ServerBootstrap：bind()
*       创建服务器端channel的辅助类
*       接收connection请求
* ClientBootstrap：connect()
*       创建客户端channel的辅助类
*       发起connection请求
* ConnectionBootsrap：connect()，bind()
*       创建无连接传输channel的辅助类(UDP)
*       包括Client和Server
*
* org.jboss.netty.buffer
* 取代nio中的java.nio.ByteBuffer, 相比ByteBuffer
*       可以根据需要自定义buffer type
*       内置混合的buffer type，以实现zero-copy
*       提供类似StringBuffer的动态dynamic buffer
*       不需要调用flip方法
*       更快的性能
*       推荐使用ChannelBuffers的静态工厂创建ChannelBuffer
* org.jboss.netty.channel
*       channel核心api，包括异步和事件驱动等各种传送接口
* org.jboss.netty.channel.group
*       channel group 帮助用户维护channel列表
* org.jboss.netty.channel.local
*       一种虚拟传输方式，允许同一个虚拟机上的两个部分可以互相通信
* org.jboss.netty.channel.socket
*       TCP,UDP接口，继承了核心的channel API
* org.jboss.netty.channel.socket.nio
*       基于nio的socket channel实现
* org.jboss.netty.channel.socket.oio
*       基于老io的socket channel实现
* org.jboss.netty.channel.socket.http
*       基于http的客户端和相应的server端的实现，工作在有防火墙的情况
* org.jboss.netty.container
*       各种容器的兼容
* org.jboss.netty.container.microcontainer
*       JBoss Microcontainer集成接口
* org.jboss.netty.container.osgi
*       osgi framework集成接口
* org.jboss.netty.container.spring
*       spring framework集成接口
* org.jboss.netty.handler
*       处理器
* org.jboss.netty.handler.execution
*       基于Executor的实现
* org.jboss.netty.handler.queue
*       将event存入内部队列的处理
* org.jboss.netty.handler.ssl
*       基于SSLEngine的ssl以及TLS实现
* org.jboss.netty.handler.stream
*       异步写入大数据，不会产生outOfMemory也不会花费很多内存
*
* org.jboss.netty.handler.timeout
*       通过Timer来对读写超时或者闲置链接进行通知
* org.jboss.netty.handler.codec.base64
*       Base64编码
* org.jboss.netty.handler.codec.compression
*       压缩格式
* org.jboss.netty.handler.codec.embedder
*       嵌入模式下编码和解码
* org.jboss.netty.handler.codec.frame
*       评估流的数据的排列和内容
* org.jboss.netty.handler.codec.http.websocket
*       websocket编码解码
* org.jboss.netty.handler.codec.http
*       http的编码解码已经类型信息
* org.jboss.netty.handler.codec.oneone
*       对象到对象编码解码
* org.jboss.netty.handler.codec.protobuf
*       Protocol Buffers的编码解码
* org.jboss.netty.handler.codec.replay
*       在阻塞io中实现非阻塞解码
* org.jboss.netty.handler.codec.rtsp
*       rtsp的编码解码
* org.jboss.netty.handler.codec.serialization
*       序列化对象到bytebuffer实现
* org.jboss.netty.handler.codec.string
*       字符串编码解码，继承oneone
* org.jboss.netty.logging
*       根据不同的log framework实现的类
* org.jboss.netty.util
*       netty.util类
* org.jboss.netty.util.internal
*       netty内部util类，不被外部使用
* */
public class NettyExample {
}






























