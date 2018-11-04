package com.meiya.nettypackage4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

  /*  @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

        //要返回的内容, Channel可以理解为连接，而连接中传输的信息要为ByteBuf
        ByteBuf content = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);

        //构造响应
        FullHttpResponse response =
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);

        //设置头信息的的MIME类型
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");  //内容类型
        //设置要返回的内容长度
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes()); //内容长度
        //将响应对象返回
        channelHandlerContext.writeAndFlush(response);
    }*/

    //通道注册成功
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel register success...");
        super.channelRegistered(ctx);
    }
  /*  //通道取消注册
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel register cancel...");
        super.channelUnregistered(ctx);
    }*/

    //通道处于活动状态，即可用状态
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel active...");
        super.channelActive(ctx);
    }

    //通道处于不活动状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channel inactive...");
        super.channelInactive(ctx);
    }

    /*
    自定义的Handler被添加,也就是在TestChannelnitializer的initChannel方法中，
    pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    这行代码执行的时候，该方法被触发
    * */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handler added...");
        super.handlerAdded(ctx);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {

    }
}




























