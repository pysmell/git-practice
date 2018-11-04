package com.meiya.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * CompletionHandler对象来处理对文件的异步写入的结果
 */
public class Test40 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Path filePath = Paths.get("d:\\", "test.txt");

        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(filePath, WRITE, CREATE);

        Attachment attachment = new Attachment();

        ByteBuffer byteBuffer = getDataBuffer();

        attachment.path = filePath;

        attachment.asynchronousFileChannel = asynchronousFileChannel;

        attachment.buffer = byteBuffer;

        asynchronousFileChannel.write(byteBuffer, 0, attachment, new WriteHandler());

        System.out.println("Sleeping for 5  seconds...");

        Thread.sleep(5000);
    }

    public static ByteBuffer getDataBuffer() {

        String lineSeparator = System.getProperty("line.separator");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("test");
        stringBuilder.append(lineSeparator);
        stringBuilder.append("test");
        stringBuilder.append(lineSeparator);
        String str = stringBuilder.toString();
        Charset charset = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes(charset));
        return byteBuffer;
    }

}

class Attachment {
    public Path path;
    public ByteBuffer buffer;
    public AsynchronousFileChannel asynchronousFileChannel;
}

class WriteHandler implements CompletionHandler<Integer, Attachment> {

    @Override
    public void completed(Integer result, Attachment attachment) {

        System.out.format("%s bytes written to %s%n", result, attachment.path.toAbsolutePath());

        try {
            attachment.asynchronousFileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Attachment attachment) {
        try {
            attachment.asynchronousFileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}