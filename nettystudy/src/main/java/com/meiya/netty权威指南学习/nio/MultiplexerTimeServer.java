package com.meiya.netty权威指南学习.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;


public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {

        try {

            serverSocketChannel = ServerSocketChannel.open();

            ServerSocket serverSocket = serverSocketChannel.socket();

            /*第二个参数请求队列的最大长度*/
            serverSocket.bind(new InetSocketAddress(port), 1024);

            selector = Selector.open();

            serverSocketChannel.configureBlocking(Boolean.FALSE);

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("The time server is start in port :" + port);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

    }

    @Override
    public void run() {

        while (!stop) {
            try {

                selector.select(1000);

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                SelectionKey key = null;

                while (iterator.hasNext()) {

                    key = iterator.next();

                    iterator.remove();

                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.channel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        stop = true;
    }

    private void handleInput(SelectionKey selectionKey) throws IOException {

        if (selectionKey.isValid()) {

            if (selectionKey.isAcceptable()) {

                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

                SocketChannel socketChannel = serverSocketChannel.accept();

                socketChannel.configureBlocking(Boolean.FALSE);

                socketChannel.register(selector, SelectionKey.OP_READ);

            }

            if (selectionKey.isReadable()) {

                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                //异步读取 返回值大于0，读到了字节，返回值等于0，没有读取到字节，属于正常情况，返回值为-1，链路已经关闭，需要关闭SocketChannel，释放资源
                int readBytes = socketChannel.read(byteBuffer);

                if (readBytes > 0) {

                    byteBuffer.flip();

                    byte[] bytes = new byte[byteBuffer.remaining()];

                    byteBuffer.get(bytes);

                    String body = new String(bytes, "UTF-8");

                    System.out.println("The time server receive order: " + body);

                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";

                    doWrite(socketChannel, currentTime);

                } else if(readBytes < 0) {

                    selectionKey.cancel();

                    socketChannel.close();

                } else {

                }

            }
        }

    }

    private void doWrite(SocketChannel channel, String response) throws IOException {

        if (response != null && response.trim().length() > 0) {

            byte[] bytes = response.getBytes();

            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);

            writeBuffer.put(bytes);

            writeBuffer.flip();
            //异步非阻塞，它并不保证一次能够把需要发送的字节数组发送完，此时可能会出现“写半包”情况
            //我们需要注册写操作，不断轮询Selector将没有发送完的ByteBuffer发送完毕，然后通过ByteBuffer的hasRemain()方法判断消息
            //是否发送完成
            channel.write(writeBuffer);
        }

    }
}






































