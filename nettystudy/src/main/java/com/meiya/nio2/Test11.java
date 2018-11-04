package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 创建临时文件，需要传入属性，需要使用第四个参数
 */
public class Test11 {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        String prefix = "log_";

        String suffix = ".txt";

        Path path = Paths.get(home + "/");

        Files.createTempFile(path, prefix, suffix);


    }

}
