package javabase.编码;

import java.io.UnsupportedEncodingException;

/**
 * UTF-8转gbk,偶数不会出现问题因为gbk中文是采用两个字节，utf-8中文是采用三个字节
 * 只要utf-8中文的字节数是2的倍数，中文转换就不会乱码
 * GBK转UTF-8,只要gbk中文的字节数是3的倍数，中文转换就不会出现乱码
 * ISO-8859-1 可以作为中间编码，不会导致数据丢失；
 * GBK 如果汉字数量为偶数，不会丢失数据，如果汉字数量为奇数，必定会丢失数据。
 */
public class EncodeTest1 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //这是一个unicode字符串，与字符集无关
        String str1 = "用户";

        System.out.println("unicode字符串:" + str1);

        //将字符串转为UTF-8字节流
        byte[] byteArray = str1.getBytes("UTF-8"); //这个很安全，UTF-8不会造成丢失
        System.out.println(byteArray.length); //打印6，没毛病

        String str2 = new String(byteArray, "GBK");

        System.out.println("转成gbk会乱码：" + str2);

        //将GBK编码的unicode字符串转回为byte[]
        byte[] byteArray2 = str2.getBytes("gbk");
        System.out.println(byteArray2.length);

        String str3 = new String(byteArray2, "utf-8");

        System.out.println(str3);
    }
}




























