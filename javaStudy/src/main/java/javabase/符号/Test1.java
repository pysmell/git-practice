package javabase.符号;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {

        String[] strings = new String[5];

        strings[0] = "1";

        List<String[]> list = new ArrayList<>();

        list.add(strings);

        strings[1] = "2";

        System.out.println("111");

        System.out.println(list);
    }

}
