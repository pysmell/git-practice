package com.meiya.nio2;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;

import static java.nio.file.StandardOpenOption.READ;

public class Test43 {

    public static void main(String[] args) {

        Path path = Paths.get("D:\\", "test.txt");

        try (AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, READ)) {

            int fileSize = (int) asynchronousFileChannel.size();

            ByteBuffer byteBuffer = ByteBuffer.allocate(fileSize);

            Future<Integer> future = asynchronousFileChannel.read(byteBuffer, 0);

            int readBytes = future.get();

            System.out.format("%s bytes read from %s%n", readBytes, path.toAbsolutePath());

            System.out.format("Read data is: %n");

            byte[] bytes = byteBuffer.array();

            Charset charset = Charset.forName("UTF-8");

            String str = new String(bytes, charset);

            System.out.println(str);

        } catch (Exception e) {

        }
    }

}





























































