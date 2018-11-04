package com.meiya.nio2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * nio2递归文件夹
 * @author linqw
 * @version 2018-06-05
 */
public class Test19 {

    public static void main(String[] args) throws IOException {

        Path listDir = Paths.get("D:\\photo");

        ListTree listTree = new ListTree();

        Files.walkFileTree(listDir, listTree);


    }

}
class ListTree extends SimpleFileVisitor<Path> {

    /**
     * 访问一个目录前要干啥
     */
    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

        System.out.println("preVisit: " + dir.toString());

        return super.preVisitDirectory(dir, attrs);
    }

    /**
     * 正在访问一个文件时要干啥
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        System.out.println("visitFile: " + file.toString());

        return FileVisitResult.CONTINUE;

    }

    /**
     * 访问一个文件失败时要干啥
     */
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {

        System.out.println("visitFile: " + exc);

        return FileVisitResult.CONTINUE;
    }

    /**
     * 访问一个目录后要干啥
     */
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

        System.out.println("postVisit: " + dir.toString());
        return FileVisitResult.CONTINUE;
    }
}
/**
 * 在旧版本中遍历文件系统只能通过递归的方法来实现，但是这种方法不仅消耗资源大而且效率低
 * Nio2的Files工具类提供了一个静态工具方法walkFileTree来高效并优雅地遍历文件系统
 * walkFileTree：
 * i. 原型：static Path Files.walkFileTree(Path start, FileVisitor<? super Path> visitor);
 * ii. 表示从start代表的节点开始遍历文件系统；
 * iii. 其中visitor是遍历过程中的行为控制器；
 * 遍历行为控制器——FileVisitor
 * 它是一个接口，里面定义了4个方法用来指定当你访问一个节点之前、之中、之后、失败时应该采取什么行动
 */
/**
 * FileVisitResult是一个枚举类型 ，walkFileTree就是会根据这个返回值决定是否要继续遍历下去，如果要继续遍历下去则应该怎样遍历
 * CONTINUE：继续遍历
 * SKIP_SIBLINGS：继续遍历，但忽略当前节点的所有兄弟节点直接返回上一层继续遍历
 * SKIP_SUBTREE：继续遍历，但是忽略子目录，但是子文件还是会访问；
 * TERMINATE：终止遍历
 */

/**
 * Path检验路径中的字符串：都是Path的对象方法
 * a. boolean startsWith(String other); // 前缀检查
 * b. boolean startsWith(Path other);
 * c. boolean endsWith(String other); // 后缀检查
 * d. boolean endsWith(Path other);
 */





















