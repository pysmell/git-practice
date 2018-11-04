package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 如果想仅一次调用就创建多级目录，当其遇任何路径中缺省的名称元素,并不抛IOException异常,而是递归地创建所有缺失的名称元素
 */
public class TestTen {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        Path dir = Paths.get(home + File.separator + "mydir_" + UUID.randomUUID().toString());

        //resolve()作用相当于把当前路径当成父目录，而把参数中的路径当成子目录或是其中的文件
        Path subdir = dir.resolve("subdir");

        System.out.println(Files.exists(dir));

        System.out.println(Files.exists(subdir));

        Files.createDirectories(subdir);

        System.out.println(Files.exists(dir));

        System.out.println(Files.exists(subdir));

    }
}
