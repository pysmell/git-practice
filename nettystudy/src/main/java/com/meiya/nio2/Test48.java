package com.meiya.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * nio2异步读取单个文件
 */
public class Test48 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Path path = Paths.get("d:\\", "Calculater.java");

        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);

        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path);

        asynchronousFileChannel.read(byteBuffer, 0, "attachment information", new CompletionHandler<Integer, String>() {

            @Override
            public void completed(Integer result, String attachment) {
                System.out.println(result);
                System.out.println("success" + attachment);
                System.out.println(new String(byteBuffer.array()));
            }

            @Override
            public void failed(Throwable exc, String attachment) {
                System.out.println("fail" + attachment);
                System.out.println("Error:" + exc);
            }
        });

        System.out.println("continue doing other thing");

        Thread.sleep(1000);
    }

}















































