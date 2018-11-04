package com.meiya.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * 使用Future对象来处理对文件的异步写入的结果
 */
public class Test41 {

    public static void main(String[] args) {

        Path filePath = Paths.get("D:\\", "test.txt");

        try(AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(filePath, WRITE, CREATE)) {

            Future<Integer> future = asynchronousFileChannel.write(getDataBuffer(), 0);
            while (!future.isDone()) {

                Thread.sleep(200);
            }

            int byteSize = future.get();

            System.out.format("%s bytes writer to %s%n", byteSize, filePath.toAbsolutePath());

        } catch (Exception e) {

        }


    }

    public static ByteBuffer getDataBuffer() {

        String lineSeparator = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        stringBuilder.append(lineSeparator);
        String str = stringBuilder.toString();
        Charset charset = Charset.forName("UTF-8");
        return ByteBuffer.wrap(str.getBytes(charset));
    }
}































































