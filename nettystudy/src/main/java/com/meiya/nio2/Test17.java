package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 递归删除目录
 */
public class Test17 {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        Path directoryPath = Paths.get(home + File.separator + "mydir_a324db15-d953-49b4-9ae0-a67e5e8ab6c0");

        System.out.println(Files.exists(directoryPath));

        deleteFile(directoryPath);

        System.out.println(Files.exists(directoryPath));
    }

    public static void deleteFile(Path directoryPath) throws IOException {

        if (Files.isDirectory(directoryPath)) {

            try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath)) {

                for (Path path : directoryStream) {

                    if (Files.isDirectory(path)) {
                        deleteFile(path);
                    } else {
                        Files.delete(path);
                    }
                }

            } catch (IOException e) {

            }

        }

        Files.delete(directoryPath);

    }
}








































