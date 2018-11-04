package javabase.编码;

import java.io.UnsupportedEncodingException;

/**
 * utf-8转gbk 出现乱码问题分析，需用ISO-8859-1进行中转utf-8
 * Unicode只是一个符号集，它只规定了符号的二进制代码，却没有规定这个二进制代码应该如何存储
 * UTF-8是Unicode的实现方式之一
 * UTF-8最大的一个特点，就是它是一种变长的编码方式。它可以使用1~4个字节表示一个符号，
 * 根据不同的符号而变化字节长度
 * 1）对于单字节的符号，字节的第一位设为0，后面7位为这个符号的unicode码。因此对于英语字母，
 * UTF-8编码和ASCII码是相同的。
 * 2）对于n字节的符号（n>1），第一个字节的前n位都设为1，第n+1位设为0，
 * 后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx10xxxxxx10xxxxxx
 */


/**
 * 设想一个场景
 * 用户A,有一个UTF-8编码字节流，通过一个接口传送给用户B
 * 用户B并不知道是什么字符集，用ISO-8859-1来接收，保存；在一定的处理流程后，把这个字节流
 * 交给用户C或者用户A,他们都知道这是UTF-8，解码得到的数据，不会丢失
 */
public class EncodeTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //这是一个unicode字符串，与字符集无关
        String str1 = "用户";

        System.out.println("unicode字符串" + str1);

        //将字符串转为UTF-8字节流
        byte[] byteArray = str1.getBytes("UTF-8"); //这个很安全，UTF-8不会造成丢失
        System.out.println(byteArray.length); //打印6，没毛病

        //将byteArray当做一个普通的字节流，按照ISO-8859-1解码为一个unicode字符串
        String str2 = new String(byteArray, "ISO-8859-1");
        System.out.println("转成ISO-8859-1会乱码：" + str2);

        //将ISO-8859-1编码的unicode字符串转回byte[]
        byte[] byteArrays = str2.getBytes("ISO-8859-1");
        System.out.println(byteArrays.length);

        //将字节流重新给用户A
        //重新用utf-8解码
        String str3 = new String(byteArrays, "utf-8");
        System.out.println("数据没有丢失：" + str3);
    }

}

















