package com.meiya.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.READ;

/**
 * 使用CompletionHandler对象来处理从文件进行异步读取的结果
 */
public class Test42 {

    public static void main(String[] args) throws IOException, InterruptedException {

        Path path = Paths.get("d:\\", "test.txt");

        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, READ);

        ReadHandler readHandler = new ReadHandler();

        ByteBuffer byteBuffer = ByteBuffer.allocate((int) asynchronousFileChannel.size());

        Attachment2 attachment2 = new Attachment2();

        attachment2.asynchronousFileChannel = asynchronousFileChannel;

        attachment2.path = path;

        attachment2.byteBuffer = byteBuffer;

        asynchronousFileChannel.read(byteBuffer, 0, attachment2, readHandler);

        System.out.println("sleep 200s");

        Thread.sleep(200);
    }

}

class Attachment2 {
    public Path path;
    public ByteBuffer byteBuffer;
    public AsynchronousFileChannel asynchronousFileChannel;
}

class ReadHandler implements CompletionHandler<Integer, Attachment2> {

    @Override
    public void completed(Integer result, Attachment2 attachment) {
        System.out.format("%s bytes read from %s%n", result, attachment.path.toAbsolutePath());
        System.out.format("Read data is:%n");
        byte[] bytes = attachment.byteBuffer.array();
        Charset charset = Charset.forName("UTF-8");
        String data = new String(bytes, charset);
        System.out.println(data);
        try {
            attachment.asynchronousFileChannel.close();
        } catch (Exception e) {

        }
    }

    @Override
    public void failed(Throwable exc, Attachment2 attachment) {

        System.out.format("Read operation on %s file failed." + "The error is: %s%n", attachment.path.toAbsolutePath(), exc.getMessage());

        try {
            attachment.asynchronousFileChannel.close();
        } catch (Exception e) {

        }
    }
}






































