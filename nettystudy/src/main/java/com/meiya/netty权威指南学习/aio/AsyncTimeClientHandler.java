package com.meiya.netty权威指南学习.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author linqw
 * @date 2018年5月23
 * @version 1.0
 */
public class AsyncTimeClientHandler implements Runnable {

    AsynchronousSocketChannel asynchronousSocketChannel;

    private String host;

    private int port;

    CountDownLatch countDownLatch;

    public AsyncTimeClientHandler(String host, int port) {

        this.host = host==null?"127.0.0.1":host;

        this.port = port;

        try {
            asynchronousSocketChannel = AsynchronousSocketChannel.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        countDownLatch = new CountDownLatch(1);

        doConnect(asynchronousSocketChannel);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doConnect(AsynchronousSocketChannel asynchronousSocketChannel) {

        asynchronousSocketChannel.connect(new InetSocketAddress(host, port), this, new ConnectCompletionHandler());

    }

}

































