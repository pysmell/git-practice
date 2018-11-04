package com.meiya.nio2;

import com.google.common.collect.Sets;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;

/**
 * 列出根目录，java6 File.listRoots()
 */
public class Test23 {

    public static void main(String[] args) {

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();

        System.out.println("Root file system locations: " + Sets.newHashSet(iterable));
    }
}
