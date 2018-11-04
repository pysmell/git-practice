package javabase.符号;

public class Test {

    public static void main(String[] args) {
        /*int a = 2;
        int b = 3;
        a = a&b;
        System.out.println(a);*/
        String str = "350628198710102550\t";
        System.out.println(str.replaceAll("\t", "").replace("x", "X"));
    }

}
