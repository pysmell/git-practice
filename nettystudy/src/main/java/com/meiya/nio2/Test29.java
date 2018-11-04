package com.meiya.nio2;

import io.netty.util.CharsetUtil;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 文件写操作
 */
public class Test29 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:\\", "text.txt");

        BufferedWriter bufferedWriter = Files.newBufferedWriter(path, CharsetUtil.UTF_8, StandardOpenOption.APPEND);

        bufferedWriter.write("测试文件写操作");

        bufferedWriter.flush();

        bufferedWriter.close();

    }

}
































