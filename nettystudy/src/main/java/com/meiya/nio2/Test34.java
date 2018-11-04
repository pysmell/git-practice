package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;

/**
 * 检查文件存储支持的文件属性视图
 */
public class Test34 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("c:");

        FileStore fileStore = Files.getFileStore(path);

        printDetails(fileStore, AclFileAttributeView.class);
        printDetails(fileStore, BasicFileAttributeView.class);
        printDetails(fileStore, DosFileAttributeView.class);
        printDetails(fileStore, FileOwnerAttributeView.class);
        printDetails(fileStore, PosixFileAttributeView.class);
        printDetails(fileStore, UserDefinedFileAttributeView.class);
    }

    public static void printDetails(FileStore fileStore, Class<? extends FileAttributeView> attributeView) {

        boolean supported = fileStore.supportsFileAttributeView(attributeView);

        System.out.format("%s is supported: %s%n", attributeView.getSimpleName(), supported);

    }

}










































