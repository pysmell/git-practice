package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * nio2递归移动文件夹到另一个文件夹
 */
public class Test22 {

    private static String sourcePath = "D:\\photo";
    private static String targetPath = "D:\\photo2";
    public static void main(String[] args) throws IOException {

        Path sourceDirPath = Paths.get(sourcePath);

        Path targetDirPath = Paths.get(targetPath);

        sourceMoveTarget(sourceDirPath, targetDirPath);
    }

    private static void sourceMoveTarget(Path sourceDirPath, Path targetDirPath) throws IOException {

        File targetDirFile = targetDirPath.toFile();

        if (!targetDirFile.exists()) {

            targetDirFile.mkdirs();

        }

        Files.walkFileTree(sourceDirPath, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                String dirName = dir.toAbsolutePath().toString();

                String subDirName = dirName.substring(dirName.indexOf(sourcePath) + sourcePath.length());

                File tempDir = new File(targetPath + File.separator + subDirName);

                if ((!tempDir.exists()) && (!sourcePath.equals(subDirName))) {

                    tempDir.mkdirs();

                }

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                String absoluteRoot = file.toAbsolutePath().toString();

                System.out.println(absoluteRoot);

                String subFileName = absoluteRoot.substring(absoluteRoot.indexOf(sourcePath) + sourcePath.length());

                System.out.println(subFileName);

                File targetFile = new File(targetDirFile, subFileName);

                Files.move(file, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });


    }
}
