package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 批量读取文件属性
 */
public class Test37 {

    public static void main(String[] args) throws IOException {

        Path dirPath = Paths.get("d:");

        BasicFileAttributes basicFileAttributes = Files.readAttributes(dirPath, BasicFileAttributes.class);

        System.out.format("Size: %s bytes %n", basicFileAttributes.size());

        System.out.format("Creation Time: %s %n", basicFileAttributes.creationTime());

        System.out.format("Last Access Time:%s %n", basicFileAttributes.lastAccessTime());
    }

}
