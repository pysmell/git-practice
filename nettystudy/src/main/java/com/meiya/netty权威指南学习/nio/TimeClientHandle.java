package com.meiya.netty权威指南学习.nio;

import java.io.IOException;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandle implements Runnable {

    private String host;

    private int port;

    private Selector selector;

    private SocketChannel socketChannel;

    private volatile boolean stop;

    public TimeClientHandle(String host, int port) {

        this.host = host == null?"127.0.0.1":host;

        this.port = port;

        try {

            selector = Selector.open();

            socketChannel = SocketChannel.open();

            socketChannel.configureBlocking(Boolean.FALSE);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void run() {

        try {
            doConnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while(!stop) {
            try {
                selector.select(100);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();
                SelectionKey key = null;
                while (selectionKeyIterator.hasNext()) {
                    key = selectionKeyIterator.next();
                    selectionKeyIterator.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        if (selector != null) {
            try {
                //所有注册在上面的Channel和Pipe等资源都会被自动去注销并关闭，所以不需要重复释放资源
                //并将所有相关的选择键设置为无效
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void doConnect() throws IOException {
        //连接是异步非阻塞，如果连接成功，则注册到多路复用器上，发送请求消息，读应答
        if(socketChannel.connect(new InetSocketAddress(host, port))) {

            socketChannel.register(selector, SelectionKey.OP_READ);

            doWrite(socketChannel);

        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }

    private void doWrite(SocketChannel socketChannel) throws IOException {

        byte[] req = "QUERY TIME ORDER".getBytes();

        ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);

        writeBuffer.put(req);

        writeBuffer.flip();

        socketChannel.write(writeBuffer);

        if (!writeBuffer.hasRemaining()) {
            System.out.println("Send order 2 server succeed");
        }
    }

    public void stop() {
        stop = true;
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {

        if (selectionKey.isValid()) {
            //判断连接是否成功
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            if (selectionKey.isConnectable()) {
                if (socketChannel.finishConnect()) {
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    doWrite(socketChannel);
                } else {
                    System.exit(1);//连接失败，进程退出
                }
            }
            if (selectionKey.isReadable()) {

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                int readBytes = socketChannel.read(byteBuffer);

                if (readBytes > 0) {

                    byteBuffer.flip();

                    byte[] bytes = new byte[byteBuffer.remaining()];

                    byteBuffer.get(bytes);

                    String body = new String(bytes, "UTF-8");

                    System.out.println("Now is:" + body);

                    this.stop = true;

                } else if (readBytes < 0) {
                    selectionKey.cancel();
                    socketChannel.close();
                } else {
                    //读到0字节，忽略
                }
            }
        }

    }
}






























































































































