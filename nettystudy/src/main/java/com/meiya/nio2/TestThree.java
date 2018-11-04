package com.meiya.nio2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 确认一个文件是普通的文件还是目录
 */
public class TestThree {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) {

        Path p = Paths.get(home);

        System.out.println(Files.isRegularFile(p));

        System.out.println(Files.isDirectory(p));
    }

}
