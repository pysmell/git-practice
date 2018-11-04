package com.meiya.netty权威指南学习.aio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

    private AsynchronousSocketChannel asynchronousSocketChannel;

    public ReadCompletionHandler(AsynchronousSocketChannel asynchronousSocketChannel) {

        if (this.asynchronousSocketChannel == null) {
            this.asynchronousSocketChannel = asynchronousSocketChannel;
        }
    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();
        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);
        try {
            String req = new String(body, "UTF-8");
            System.out.println("The Timer server receive order : " + req);
            String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(req)?new Date(System.currentTimeMillis()).toString():"BAD ORDER";
            doWrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.asynchronousSocketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWrite(String currentTime) {

        if (currentTime != null && currentTime.trim().length() > 0) {

            byte[] bytes = currentTime.getBytes();

            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);

            byteBuffer.put(bytes);

            byteBuffer.flip();

            asynchronousSocketChannel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {

                    //如果没有发送完成，继续发送
                    if (attachment.hasRemaining()) {
                        asynchronousSocketChannel.write(attachment, attachment, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        //io异常直接进行关闭资源，其他异常进行业务处理
                        asynchronousSocketChannel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }
}

























