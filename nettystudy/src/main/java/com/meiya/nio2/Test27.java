package com.meiya.nio2;

import java.io.File;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Path用于来表示文件路径和文件，可以有多种方法来构造一个Path对象来表示一个文件路径，或者一个文件
 */
public class Test27 {

    public static void main(String[] args) {

        Path path = Paths.get("d:/" ,"photo2");

        Path path1 = Paths.get("d:\\photo2");

        URI uri = URI.create("file://d:\\photo2");

        Path path2 = Paths.get(uri);

        Path path3 = FileSystems.getDefault().getPath("d:\\", "access.log");

        //File和Path之间的转换，File和URI之间的转换

        File file = new File("d:\\photo2");

        Path path4 = file.toPath();

        path4.toFile();

        path4.toUri();
    }

}











































