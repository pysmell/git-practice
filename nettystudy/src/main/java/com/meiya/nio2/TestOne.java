package com.meiya.nio2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 确认一个文件或者目录是否存在
 * @author linqw
 */
public class TestOne {

    private static String HOME = System.getProperty("user.home");

    public static void main(String[] args) {

        System.out.println(HOME);
        //Path实例来表示一个文件或者目录
        Path path = Paths.get(HOME);

        System.out.println(Files.exists(path));

    }

}
