package CollectionStudy.package1;

import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

/*
线程安全,现在较少使用，内部也是由数组，有一个当向量的容量大于其容量时，向量的容量自动递增的量。如果
容量增量小于或等于零，容量向量每次需要增长一倍。
* */
public class VectorTest {

    public static void main(String[] args) {

        Vector<String> list = new Vector<>();
        list.add("tom");
        list.add("toe");
        list.add("ruby");
        Enumeration enumeration = list.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }

}
