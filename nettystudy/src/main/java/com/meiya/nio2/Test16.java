package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 当操作文件夹而非文件时，删除操作默认不进行递归操作，删除非空文件夹会报错java.nio.file.DirectoryNotEmptyException
 */
public class Test16 {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(home + File.separator + "mydir_" + UUID.randomUUID().toString());

        Files.createDirectory(path);

        System.out.println(Files.exists(path));

        Path subFile = path.resolve("file.txt");

        Files.createFile(subFile);

        Files.delete(path);

        System.out.println(Files.exists(path));
    }

}
