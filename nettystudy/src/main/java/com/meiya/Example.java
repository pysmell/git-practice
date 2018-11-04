package com.meiya;

public class Example {

    public static void main(String[] args) {

        String s = new String("abc");

        String s1 = "abc";

        StringBuffer stringBuffer = new StringBuffer("abc");

        StringBuffer stringBuffer1 = new StringBuffer("abc");

        System.out.println(s == s1);

        System.out.println(s.equals(s1));

        System.out.println(s.hashCode() == s1.hashCode());

        System.out.println(stringBuffer == stringBuffer1);

        System.out.println(stringBuffer.equals(stringBuffer1));

        System.out.println(stringBuffer.hashCode() == stringBuffer1.hashCode());

        System.out.println(s.equals(stringBuffer));

        System.out.println(stringBuffer.equals(s));

    }
}
