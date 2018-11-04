package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 创建文件，所有出现的名称元素必须存在，除了文件名，否则将出现IOException
 */
public class TestEight {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        String fileName = "myFile_" + UUID.randomUUID().toString() + ".txt";

        Path path = Paths.get(home + File.separator + fileName);

        if (!(Files.exists(path))) {

            Files.createFile(path);
        }

        System.out.println(Files.exists(path));
    }

}
