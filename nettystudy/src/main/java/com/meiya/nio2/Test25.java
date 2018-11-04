package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *  newDirectoryStream(Path dir, String glob) 用GLOB做过滤
 */
public class Test25 {

    public static void main(String[] args) {
        String dirRoot = "D:\\photo2";
        Path dirPath = Paths.get(dirRoot);
        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(dirPath, "2018-02*")) {
            for (Path path : directoryStream) {
                System.out.println(path.toString());
            }
        } catch (IOException e) {
        }
    }
}
/**
 * *.txt 匹配所有扩展名为.txt的文件
 * *.{html,htm} 匹配所有扩展名为.html或.htm的文件，{}用于组模式，使用逗号分隔
 * ?.txt 匹配任何单个字符做文件名并且扩展名为.txt的文件
 * . 匹配所有含扩展名的文件
 * C:\Users\* 匹配所有在C盘Users目录下的文件
 * [xyz].txt 匹配所有含单个字符作为文件名，且单个字符只含"x"或"y"或"z"三种之一，且扩展名为.txt的文件。方括号用于指定一个集合
 * [a-c].txt 匹配所有单个字符作为文件名，且单个字符只含“a”或“b”或“c”三种之一，且扩展名为.txt的文件。减号“-”用于指定一个范围，且只能用在方括号[]内
 * [!a].txt 匹配所有单个字符作为文件名，且单个字符不能包含字母“a”，且扩展名为.txt的文件。叹号“!”用于否定
 */

































