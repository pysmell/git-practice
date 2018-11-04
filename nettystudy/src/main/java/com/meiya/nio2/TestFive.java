package com.meiya.nio2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 检查一个文件是否可写的
 */
public class TestFive {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) {

        Path path = Paths.get(home);

        System.out.println(Files.isWritable(path));

    }

}
