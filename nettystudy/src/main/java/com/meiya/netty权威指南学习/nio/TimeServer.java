package com.meiya.netty权威指南学习.nio;

/**
 * 服务端
 */
public class TimeServer {

    public static void main(String[] args) {

        int port = 8080;

        if (args != null && args.length >0) {
            try {

                port = Integer.valueOf(args[0]);

            } catch (NumberFormatException e) {

            }
        }

        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);

        new Thread(multiplexerTimeServer, "multiplexerTimeServer-1").start();

    }

}
