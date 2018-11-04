package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;

/**
 * 文件属性视图支持
 */
public class Test33 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("");

        FileStore fileStore = Files.getFileStore(path);

        boolean flag = fileStore.supportsFileAttributeView(PosixFileAttributeView.class);

        if (flag) {
            System.out.println("POSIX file attribute view  is supported.");
        } else {
            System.out.println("POSIX file attribute view  is not supported.");
        }
    }

}





























