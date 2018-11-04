package com.meiya.netty权威指南学习.netty.package14;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;

/**
 * 每次调用都会产生新的实例
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServerHandler.class);

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        //传统的HTTP接入,可能是普通的接入，也可能是websocket接入，因为第一次websocket连接也是基于http连接，头Upgrade：websocket, Connection:Upgrade
        if (o instanceof FullHttpRequest) {
            handleHttpRequest(channelHandlerContext, (FullHttpRequest) o);
        }
        //webSocket接入,可能是断开请求,ping请求
        else if (o instanceof WebSocketFrame) {
            handlerWebSocketFrame(channelHandlerContext, (WebSocketFrame) o);
        }
    }

    private void handleHttpRequest(ChannelHandlerContext context, FullHttpRequest request) {

        //如果HTTP解码失败，并且不是webSocket请求，返回HTTP异常
        if (!request.getDecoderResult().isSuccess() || (!"websocket".equals(request.headers().get("Upgrade")))) {
            sendHttpResponse(context, request, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //构造握手响应返回，本机测试, subprotocols - 支持的协议的CSV。如果不支持子协议，则为空。allowExtensions - 允许在web套接字帧的保留位中使用扩展名
        WebSocketServerHandshakerFactory webSocketServerHandshakerFactory = new WebSocketServerHandshakerFactory("ws://localhost:8080/websocket", null, false);
        //实例一个新的握手
        handshaker = webSocketServerHandshakerFactory.newHandshaker(request);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(context.channel());
        } else {
            handshaker.handshake(context.channel(), request);
        }
        System.out.println("handshaker" + handshaker);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("WebSocketServerHandler exceptionCaught: " + cause.getMessage());
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    private static void sendHttpResponse(ChannelHandlerContext context, FullHttpRequest request, FullHttpResponse response) {
        if (response.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(), CharsetUtil.UTF_8);
            response.content().writeBytes(buf);
            buf.release();
            setContentLength(response, response.content().readableBytes());
        }
        ChannelFuture channelFuture = context.channel().writeAndFlush(response);

        if (!isKeepAlive(request) || response.getStatus().code() != 200) {
            channelFuture.addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void handlerWebSocketFrame(ChannelHandlerContext context, WebSocketFrame frame) {
        System.out.println("handshaker" + handshaker);
        //判断是否是关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(context.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }

        //判断是否是ping消息
        if (frame instanceof PingWebSocketFrame) {
            new PongWebSocketFrame(frame.content().retain());
            return;
        }
        //仅支持文本消息，不支持二进制消息
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        //返回应答消息
        String request = ((TextWebSocketFrame)frame).text();
        /*LOGGER.info("%s received %s", context.channel(), request);*/
        System.out.println("received client info : " + request);
        context.channel().write(new TextWebSocketFrame(request + ", 欢迎使用Netty WebSocket服务，现在时刻：" + new Date().toString()));
    }

}
