package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 递归复制文件夹到另一个文件夹
 */
public class CopyFileAndDirectoryRecursivelyUsingNIO2 {

    private static String sourcePath = "D:\\photo";

    private static String targetPath = "D:\\photo2";

    public static void main(String[] args) throws Exception {

        CopyFileAndDirectoryRecursivelyUsingNIO2 P_X_Test1_ListSubFiles = new CopyFileAndDirectoryRecursivelyUsingNIO2();

        P_X_Test1_ListSubFiles.listSubFileX(sourcePath, targetPath);

    }

    public void listSubFileX(String pathName, String targetString) throws Exception {

        Path startPath = Paths.get(pathName);

        Path target = Paths.get(targetString);

        File fileTarget = target.toFile();

        if (!fileTarget.exists()) {

            fileTarget.mkdirs();

        }

        Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {

            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                String fileAbsPath = file.toAbsolutePath().toString();

                String subStringOfSource = fileAbsPath.substring(fileAbsPath.indexOf(pathName) + pathName.length());

                File tempFile = new File(fileTarget, subStringOfSource);

                Path target = tempFile.toPath();

                Files.copy(file, target, StandardCopyOption.REPLACE_EXISTING);

                return FileVisitResult.CONTINUE;

            }

            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                String dirAbsPath = dir.toAbsolutePath().toString();

                String subStringOfSource = dirAbsPath.substring(dirAbsPath.indexOf(pathName) + pathName.length());

                File tempFile = new File(fileTarget, subStringOfSource);

                if (!tempFile.exists() && !pathName.equals(subStringOfSource)) {

                    tempFile.mkdirs();

                }

                return FileVisitResult.CONTINUE;

            }

        });

    }

}
