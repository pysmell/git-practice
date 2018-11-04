package com.meiya.netty权威指南学习.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable {

    private Socket socket = null;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        PrintWriter printWriter = null;

        BufferedReader bufferedReader = null;

        String currentTime = null;

        String body = null;

        try {

            bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

            printWriter = new PrintWriter(this.socket.getOutputStream(), true);

            while (true) {

                body = bufferedReader.readLine();

                if (body == null) {
                    break;
                }

                System.out.println("The timer server receive order: " + body);

                currentTime = "Query Current Time".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"error request";

                printWriter.println(currentTime);
            }

        } catch (Exception e) {

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
            if (this.socket != null) {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
























