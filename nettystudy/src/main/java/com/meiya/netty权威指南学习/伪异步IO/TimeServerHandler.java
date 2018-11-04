package com.meiya.netty权威指南学习.伪异步IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {

    private Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        PrintWriter printWriter = null;

        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printWriter = new PrintWriter(socket.getOutputStream(), true);

            String body;

            String currentTime;

            while (true) {

                body = bufferedReader.readLine();

                if (body == null) {
                    break;
                }
                System.out.println("The server receive data: " + body);

                currentTime = "Query Current Time".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"error data";

                printWriter.println(currentTime);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (printWriter != null) {
                printWriter.close();
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
