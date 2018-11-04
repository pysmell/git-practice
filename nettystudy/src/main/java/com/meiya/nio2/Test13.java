package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 创建临时文件，不包括文件目录，也不包括前后缀，默认的文件目录为C:\Users\13765\AppData\Local\Temp
 * 文件名也只会包括那个长整型和一个默认的.tmp
 */
public class Test13 {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        Files.createTempFile(null, null);

    }

}
