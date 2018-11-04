package com.meiya.nio2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 确认一个文件不存在
 * @author linqw
 */
public class TestTwo {

    private static String userHome = System.getProperty("user.home");

    public static void main(String[] args) {

        System.out.println(userHome);

        Path p = Paths.get(userHome + "/inexieten_file.txt");

        System.out.println(Files.notExists(p));


    }

}
