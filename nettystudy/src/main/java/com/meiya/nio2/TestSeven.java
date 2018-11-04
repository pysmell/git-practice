package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 当有两条路径时，检查他们是否都指向了文件系统下的同一个文件源
 */
public class TestSeven {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        Path path1 = Paths.get(home);

        Path path2 = Paths.get(home);

        System.out.println(Files.isSameFile(path1, path2));
    }
}

































