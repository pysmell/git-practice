package com.meiya.netty权威指南学习.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.CompletionHandler;

public class ConnectCompletionHandler implements CompletionHandler<Void, AsyncTimeClientHandler> {

    @Override
    public void completed(Void result, AsyncTimeClientHandler attachment) {
        byte[] req = "QUERY TIME ORDER".getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        writeBuffer.put(req);
        writeBuffer.flip();
        attachment.asynchronousSocketChannel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachmentBuffer) {
                if (attachmentBuffer.hasRemaining()) {
                    attachment.asynchronousSocketChannel.write(attachmentBuffer, attachmentBuffer, this);
                } else {
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    attachment.asynchronousSocketChannel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer readAttachment) {
                            readAttachment.flip();
                            System.out.println(readAttachment.remaining());
                            byte[] bytes = new byte[readAttachment.remaining()];
                            System.out.println(bytes.length + bytes[0]);
                            readAttachment.get(bytes);

                            String body;

                            try {
                                body = new String(bytes, "UTF-8");

                                System.out.println("NOW is: " + body);

                                attachment.countDownLatch.countDown();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer readAttachment) {
                            try {
                                attachment.asynchronousSocketChannel.close();
                                attachment.countDownLatch.countDown();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachmentBuffer) {
                try {
                    attachment.asynchronousSocketChannel.close();
                    attachment.countDownLatch.countDown();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, AsyncTimeClientHandler attachment) {
        try {
            attachment.asynchronousSocketChannel.close();
            attachment.countDownLatch.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
