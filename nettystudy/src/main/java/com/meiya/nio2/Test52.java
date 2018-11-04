package com.meiya.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * 异步写文件，返回Future
 */
public class Test52 {

    public static void main(String[] args) throws IOException {

        Path filePath = Paths.get("d:\\","test.txt");

        Path filePath1 = Paths.get("d:\\","test2.txt");

        AsynchronousFileChannel asynchronousFileChannel1 = AsynchronousFileChannel.open(filePath, StandardOpenOption.READ, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        AsynchronousFileChannel asynchronousFileChannel2 = AsynchronousFileChannel.open(filePath1, StandardOpenOption.READ, StandardOpenOption.CREATE, StandardOpenOption.WRITE);

        Future<Integer> future = asynchronousFileChannel1.write(ByteBuffer.wrap("Sample".getBytes()),0);

        Future<Integer> future1 = asynchronousFileChannel2.write(ByteBuffer.wrap("box".getBytes()),0);

        int result;


    }
}
