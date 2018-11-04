package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 创建符号链接，软链接
 */
public class Test30 {

    public static void main(String[] args) throws IOException {

        Path existingFilePath = Paths.get("D:", "text.txt");

        Path symLinkPath = Paths.get("D:", "text_link.txt");

        Files.createLink(symLinkPath, existingFilePath); //创建硬链接
    }

}
