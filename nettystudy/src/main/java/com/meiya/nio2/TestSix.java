package com.meiya.nio2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 检查文件是可执行的
 */
public class TestSix {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) {

        Path path = Paths.get(home);

        System.out.println(Files.isExecutable(path));

    }
}
