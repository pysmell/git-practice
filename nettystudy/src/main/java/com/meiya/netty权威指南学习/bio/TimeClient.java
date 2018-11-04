package com.meiya.netty权威指南学习.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TimeClient {

    public static void main(String[] args) throws IOException {

        Socket socket = null;

        PrintWriter printWriter = null;

        BufferedReader bufferedReader = null;

        try {
            socket = new Socket();

            socket.connect(new InetSocketAddress("127.0.0.1",8090));

            printWriter = new PrintWriter(socket.getOutputStream(), true);

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printWriter.println("Query Current Time");

            String resp = bufferedReader.readLine();

            System.out.println("Now is :" + resp);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }
}
