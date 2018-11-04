package com.meiya.nio2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/**
 * 移动文件
 */
public class Test21 {

    public static void main(String[] args) throws IOException {

        List<String> list = new ArrayList<>();

        list.add("123");

        Path sourceDirPath = Paths.get("D:/dir1");

        Path targetDirPath = Paths.get("D:/dir2");

        if (!Files.exists(sourceDirPath)) {

            Files.createDirectories(sourceDirPath);

        }

        if (!Files.exists(targetDirPath)) {
            Files.createDirectories(targetDirPath);
        }

        Path sourceFile = sourceDirPath.resolve("myFile.txt");

        Path targetFile = targetDirPath.resolve("myFile2.txt");

        Files.createFile(sourceFile);

        Files.write(sourceFile, list, StandardCharsets.UTF_8);

        Files.move(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);

    }

}
