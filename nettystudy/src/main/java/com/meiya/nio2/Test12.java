package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 创建临时文件，不指定前后缀，文件只会包括那个长整型和一个默认的.tmp
 */
public class Test12 {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(home + "/");

        Files.createTempFile(path, null, null);

    }

}
