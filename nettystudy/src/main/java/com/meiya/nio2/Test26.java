package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

/**
 * newDirectoryStream (Path dir,DirectoryStream.Filterfilter)
 * 用DirectoryStream.Filter做过滤
 * 文件的大小必须是偶数
 * 执行时间的毫秒数必须是偶数
 */
public class Test26 {

    public static void main(String[] args) {

        Path directoryPath = Paths.get("D:\\", "photo2");

        DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {
            @Override
            public boolean accept(Path entry) throws IOException {
                //LinkOption.NOFOLLOW_LINKS 不支持符号链接
                long size = Files.readAttributes(entry, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS).size();

                long mills = new Date().getTime();

                boolean isSizeEvenNumber = (size % 2 == 0);

                boolean isMillEvenNumber = (mills % 2 == 0);

                return isSizeEvenNumber && isMillEvenNumber;
            }
        };

        if (Files.isDirectory(directoryPath)) {

            try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(directoryPath, filter)) {

                for (Path path : directoryStream) {
                    System.out.println(path.toString());
                }

            } catch (Exception e) {

            }

        }
    }
}
































