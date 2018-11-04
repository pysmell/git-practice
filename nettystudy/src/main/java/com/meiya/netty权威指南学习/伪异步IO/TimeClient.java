package com.meiya.netty权威指南学习.伪异步IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) {

        Socket socket = null;

        PrintWriter printWriter = null;

        BufferedReader bufferedReader = null;

        try {
            socket = new Socket();

            socket.connect(new InetSocketAddress("127.0.0.1",8090));

            printWriter = new PrintWriter(socket.getOutputStream(), true);

            printWriter.println("Query Current Time");

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String body = bufferedReader.readLine();

            System.out.println("client receive data: " + body);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
