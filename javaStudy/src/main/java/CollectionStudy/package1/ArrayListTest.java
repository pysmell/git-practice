package CollectionStudy.package1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
* list有序，可重复
* 底层是两个数组，查询快，增删慢，主要用在多查询，少修改的情况下
* 可序列化，可clone
* 线程不安全
* */
public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("tom");
        list.add("toe");
        list.add("ruby");

        List<String> list1 = new ArrayList<>();

        list1.add("toe");
        list1.add("tom");
      /*  String[] strings = new String[] {"1", "2", "3", "4", "5", "6"};
       *//* for (String str: strings) {
            System.out.println(str);
        }*//*
        String[] strings1 = list.toArray(strings);
        for (String string : strings1) {
            System.out.println(string);
        }*/

        System.out.println(list.containsAll(list1));
       /* list.remove(-1);
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
    }
}
