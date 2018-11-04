package com.meiya.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 我们可以避免这种情况通过使用deleteIfExists，即使文件不存在删除也不会报错，当多线程做删除操作时，我们不希望
 * 仅仅因为一个线程在当前线程之前做删除操作就报错误信息:
 */
public class Test15 {

    private static String home = System.getProperty("user.home");

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(home + File.separator + "inexistentFile.txt");

        System.out.println(Files.deleteIfExists(path));

    }

}
