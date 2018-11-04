package com.meiya.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Future实现异步IO
 */
public class Test49 {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(Paths.get("d:\\", "Calculater.java"));

        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);

        Future<Integer> future = asynchronousFileChannel.read(byteBuffer, 0);

        while (!future.isDone()) {
            System.out.println(new String(byteBuffer.array(), 0, future.get()));
        }
    }
}
