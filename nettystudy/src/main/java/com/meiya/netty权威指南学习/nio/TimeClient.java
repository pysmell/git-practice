package com.meiya.netty权威指南学习.nio;

/**
 * 客户端
 */
public class TimeClient {

    public static void main(String[] args) {

        int port = 8080;

        if (args != null && args.length > 0) {

            try {

                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {
                //采用默认值
            }
        }

        new Thread(new TimeClientHandle("127.0.0.1", port), "EchoClient-1").start();
    }





}
/**
 * 步骤
 * 1、打开SocketChannel
 * 2、设置SocketChannel为非阻塞模式，同时设置TCP参数
 * 3、异步连接服务端
 * 4、判断连接结构，如果连接成功，调用步骤10，否则执行步骤5
 * 5、向Reactor线程的多路复用器注册OP_CONNECT事件
 * 6、创建Selector，启动线程
 * 7、Selector轮询就绪的Key
 * 8、handleConnect()
 * 9、判断连接是否完成，完成执行步骤10
 * 10、向多路复用器注册读事件OP_READ
 * 11、handleRead()异步读请求消息到ByteBuffer
 * 12、decode请求消息
 * 13、异步写ByteBuffer到SererSocketChannel
 */













































































