package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

/**
 * 使用基本属性视图来读取和更新基本文件属性
 */
public class Test38 {

    public static void main(String[] args) {

        Path dirPath = Paths.get("D:", "person.txt");

        try {
            BasicFileAttributeView bfv = Files.getFileAttributeView(dirPath,
                    BasicFileAttributeView.class);
            BasicFileAttributes bfa = bfv.readAttributes();

            System.out.format("Size:%s bytes %n", bfa.size());
            System.out.format("Creation  Time:%s %n", bfa.creationTime());
            System.out.format("Last Access  Time:%s %n", bfa.lastAccessTime());

            FileTime newLastModifiedTime = null;
            FileTime newLastAccessTime = null;
            FileTime newCreateTime = FileTime.from(Instant.now());

            bfv.setTimes(newLastModifiedTime, newLastAccessTime, newCreateTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
