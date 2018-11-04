package com.meiya.netty权威指南学习.伪异步IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) throws IOException {

        int port = 8090;

        ServerSocket serverSocket = null;

        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }

        try {
            serverSocket = new ServerSocket();

            serverSocket.bind(new InetSocketAddress("127.0.0.1", port));

            System.out.println("The time server is start in port: " + port);

            TimeServerHandlerExecutePool timeServerHandlerExecutePool = new TimeServerHandlerExecutePool(50, 10000);

            while (true) {

                Socket socket = serverSocket.accept();

                timeServerHandlerExecutePool.execute(new TimeServerHandler(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                System.out.println("The time server is close");
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
