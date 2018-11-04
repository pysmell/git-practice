package com.meiya.nio2;

import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件读操作
 */
public class Test28 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("D:\\", "text.txt");

        BufferedReader bufferedReader = Files.newBufferedReader(path, CharsetUtil.UTF_8);

        String str = null;

        while ((str = bufferedReader.readLine()) != null) {
            System.out.println(str);
        }

    }

}
