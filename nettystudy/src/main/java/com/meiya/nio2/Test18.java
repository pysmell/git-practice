package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 复制文件
 * 如果目标文件已存在,则会复制失败,除非指明REPLACE_EXISTING
 */
public class Test18 {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        String content = "123";

        List<String> list = new ArrayList<>();

        list.add(content);

        Path dir1 = Paths.get(home + File.separator + "dir1_" + UUID.randomUUID().toString());

        Path dir2 = Paths.get(home + File.separator + "dir2_" + UUID.randomUUID().toString());

        Files.createDirectories(dir1);

        Files.createDirectories(dir2);

        Path file1 = dir1.resolve("myFile.txt");

        Files.createFile(file1);

        Files.write(file1, list, StandardCharsets.UTF_8, StandardOpenOption.APPEND);

        Path file2 = dir2.resolve("myFile2.txt");

        Files.copy(file1, file2, StandardCopyOption.REPLACE_EXISTING);
    }

}
