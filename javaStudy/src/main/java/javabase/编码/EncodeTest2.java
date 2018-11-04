package javabase.编码;

import java.io.UnsupportedEncodingException;

public class EncodeTest2 {

    public static void demo(String string, String sourceEncode, String targetEncode) throws UnsupportedEncodingException {

        System.out.println("原文：" + string);

        byte[] utfByte = string.getBytes(sourceEncode);
        System.out.println("utf Byte:");
        printHex(utfByte);
        String gbk = new String(utfByte, targetEncode); //这里实际上把数据破坏了
        System.out.println("to GBK: " + gbk);

        byte[] gbkByte = gbk.getBytes(targetEncode);
        System.out.println("gbk Byte:");
        printHex(gbkByte);

        String utf = new String(gbkByte, sourceEncode);

        System.out.println("revert UTF-8: " + utf);

        System.out.println("==========================");
    }

    private static void printHex(byte[] utfByte) {

        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : utfByte) {

            stringBuilder.append((b>>4)&0xf);
            stringBuilder.append(b&0xf);
            stringBuilder.append(" ");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void main(String[] args) throws Exception {
        String str1 = "姓名";
        String str2 = "用户名";
        demo(str1,"UTF-8","ISO-8859-1");
        demo(str2,"UTF-8","ISO-8859-1");

        demo(str1,"UTF-8","GBK");
        demo(str2,"UTF-8","GBK");
    }

}
/**
 前三段都没问题，最后一段，奇数个汉字的utf-8字节流转成GBK字符串，再转回来，前面一切正常，最后一个字节，变成了 “0x3f”，即”?”
 我们使用”用户名” 三个字来分析，它的UTF-8 的字节流为：
 [e7 94 a8] [e6 88 b7] [e5 90 8d]
 我们按照三个字节一组分组，他被用户A当做一个整体交给用户B。
 用户B由于不知道是什么字符集，他当做GBK处理，因为GBK是双字节编码，如下按照两两一组进行分组：
 [e7 94] [a8 e6] [88 b7] [e5 90] [8d ？]
 不够了，怎么办？它把 0x8d当做一个未知字符，用一个半角Ascii字符的 “？” 代替，变成了：
 [e7 94] [a8 e6] [88 b7] [e5 90] 3f
 数据被破坏了。为什么 ISO-8859-1 没问题因为 ISO-8859-1 是单字节编码，因此它的分组方案是：
 [e7] [94] [a8] [e6] [88] [b7] [e5] [90] [8d]因此中间不做任何操作，交回个用户A的时候，数据没有变化。
 */





















