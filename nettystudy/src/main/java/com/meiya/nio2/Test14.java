package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 删除一个文件，如果文件不存在于操作系统，删除操作会失败，并报错IOException
 */
public class Test14 {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(home + File.separator + "myFile.txt");

        Files.createFile(path);

        System.out.println(Files.exists(path));

        Files.delete(path);

        System.out.println(Files.exists(path));
    }
}
