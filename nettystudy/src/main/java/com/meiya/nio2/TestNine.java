package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 创建目录,要求path中所有的名字元素都存在，如果不是会得到IOException
 */
public class TestNine {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        String dirName = "myDir_" + UUID.randomUUID().toString();

        Path path = Paths.get("d:" + File.separator + dirName);

        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
        System.out.println(Files.exists(path));
    }
}









































