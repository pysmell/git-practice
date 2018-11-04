package com.meiya.netty权威指南学习.aio;

/**
 * 服务端，NIO2的异步套接字通道是真正的异步非阻塞I/O
 * 通过java.util.concurrent.Future类来表示异步操作的结果
 * 在执行异步操作的时候传入一个java.nio.channels
 */
public class TimeServer {

    public static void main(String[] args) {

        int port = 8080;

        if (args != null && args.length > 0) {

            try {

                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {
                //采用默认
            }
        }

        AsyncTimeServerHandler asyncTimeServerHandler = new AsyncTimeServerHandler(port);

        new Thread(asyncTimeServerHandler, "AIO-AsyncTimeServerHandler-001").start();

    }
}




































