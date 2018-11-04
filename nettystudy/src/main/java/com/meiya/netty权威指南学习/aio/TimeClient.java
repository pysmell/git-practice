package com.meiya.netty权威指南学习.aio;

/**
 * 客户端
 */
public class TimeClient {

    public static void main(String[] args) {

        int port = 8080;

        String host = "127.0.0.1";

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                //采用默认值
            }
        }

        AsyncTimeClientHandler asyncTimeClientHandler = new AsyncTimeClientHandler(host, port);

        new Thread(asyncTimeClientHandler, "AIO-AsyncTimeClientHandler-001").start();

    }

}






































