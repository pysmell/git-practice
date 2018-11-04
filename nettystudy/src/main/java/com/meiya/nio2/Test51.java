package com.meiya.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Future异步读文件
 */
public class Test51 {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        Path path = Paths.get("d:\\", "Calculater.java");

        AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1000);

        Future<Integer> future = asynchronousFileChannel.read(byteBuffer, 0);

        while (!future.isDone()) {

            ProfitCalculator.calculateTax();

        }

        Integer byteRead = future.get();

        System.out.println("Bytes read [" + byteBuffer + "]");
    }

}
class ProfitCalculator {

    public ProfitCalculator() {
    }

    public static void calculateTax() {

    }
}
