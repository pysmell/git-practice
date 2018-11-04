package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 读取和更新文件单个属性
 */
public class Test36 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("d:");

        System.out.println(Files.getAttribute(path, "size"));
    }
}
