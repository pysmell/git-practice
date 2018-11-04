package javabase;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TestStatic {

    public static String name;

    public static int age;

    public static TestStatic testStatic = new TestStatic();

    public TestStatic() {

        System.out.println("初始化方法");

    }

    static {
        System.out.println("静态块");
        name = "linqw";
        age = 20;
    }

    public static void main(String[] args) {
        System.out.println(TestStatic.name);
        System.out.println(TestStatic.age);
        Set<String> stringSet = new HashSet<>();
        Set<String> stringSet1 = new TreeSet<>();
    }


}
