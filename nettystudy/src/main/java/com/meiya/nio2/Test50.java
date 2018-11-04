package com.meiya.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步IO，完整的读取文件所有内容
 */
public class Test50 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Path path = Paths.get("d:\\", "Calculater.java");

        ExecutorService executorService = new ScheduledThreadPoolExecutor(3);

        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, EnumSet.of(StandardOpenOption.READ),executorService);

        CompletionHandler<Integer, ByteBuffer> completionHandler = new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println(new String(attachment.array()));
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("fail");
            }
        };

        int byteCount = 5;

        ByteBuffer[] buffers = new ByteBuffer[5];

        for (int i = 0; i < byteCount; i++) {

            buffers[i] = ByteBuffer.allocate(20);

            asynchronousFileChannel.read(buffers[i], i*20, buffers[i], completionHandler);

        }

        executorService.awaitTermination(1, TimeUnit.SECONDS);
    }


}
