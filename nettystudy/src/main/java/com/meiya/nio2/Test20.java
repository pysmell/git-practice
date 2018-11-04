package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 递归复制文件夹下的所有文件到另一个目录
 */
public class Test20 {

    public static void main(String[] args) throws IOException {

        Path sourcePath = Paths.get("D:\\photo");

        Path targetPath = Paths.get("D:\\photo2");

        ListTreeVisitor listTreeVisitor = new ListTreeVisitor(targetPath);

        Files.walkFileTree(sourcePath, listTreeVisitor);

    }

}
class ListTreeVisitor extends SimpleFileVisitor<Path> {

    private Path targetPath;

    public ListTreeVisitor(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

       /* targetPath.resolve(dir);

        Files.createDirectories(targetPath);*/

        return FileVisitResult.CONTINUE;

    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);

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
}