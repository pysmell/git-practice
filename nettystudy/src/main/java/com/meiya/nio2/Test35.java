package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 读取和更新文件属性
 */
public class Test35 {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("d:");

        BasicFileAttributes basicFileAttributes =  Files.readAttributes(path, BasicFileAttributes.class);

        //获取上一次修改时间
        FileTime lastModifiedTime = basicFileAttributes.lastModifiedTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(lastModifiedTime.toMillis());
        Date date = c.getTime();
        System.out.println("修改时间：" + sdf.format(date));
        System.out.println("文件大小：" + basicFileAttributes.size());
    }

}
/**
 * Files
 * 读取或更新一个文件属性
 * Object getAttribute(Path path, String attribute, LinkOption... options)
 * Path setAttribute(Path path, String attribute, Object value, LinkOption... options)
 * 读取或更新文件的多个属性
 * <A extends BasicFileAttributes> A readAttributes(Path path, Class<A> type, LinkOption... options)
 * Map<String,Object> readAttributes(Path path, String attributes, LinkOption... options)
 */








































