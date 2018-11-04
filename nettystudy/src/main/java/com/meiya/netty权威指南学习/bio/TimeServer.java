package com.meiya.netty权威指南学习.bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
* 并发量大，系统性能急剧下降，系统会发生线程堆栈溢出、创建新线程失败
* 无法满足高性能、高并发接入场景
* */
public class TimeServer {

    public static void main(String[] args) {

        int port = 8090;

        ServerSocket serverSocket = null;

        Socket socket = null;

        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        try {

            serverSocket = new ServerSocket();

            serverSocket.bind(new InetSocketAddress("127.0.0.1",port));

            System.out.println("The time server is start in port: " + port);

            while (true) {

                socket = serverSocket.accept();

                System.out.println("接入");

                new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (Exception e) {

        } finally {
            if (serverSocket != null) {
                try {
                    System.out.println("The time server close");
                    serverSocket.close();
                    serverSocket = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
