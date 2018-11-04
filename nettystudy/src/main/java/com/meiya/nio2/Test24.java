package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 列举目录内容
 */
public class Test24 {

    public static void main(String[] args) {

        String dirPath = "d:\\photo2";

        Path path = Paths.get(dirPath);

        if (!Files.isDirectory(path)) {
            return;
        }

        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {

            for (Path filePath : directoryStream) {

                System.out.println(filePath.toString());

            }

        } catch (IOException e) {

        }

    }
}
/**
 * newDirectoryStream有三种重载方法
 * newDirectoryStream(Path dir)
 * newDirectoryStream(Path dir, String glob)
 * - 用GLOB做过滤
 * newDirectoryStream (Path dir,DirectoryStream.Filterfilter)
 * - 用DirectoryStream.Filter做过滤
 */
