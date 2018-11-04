package IOStudy.inputstreamstudy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Example01 {

    public static void main(String[] args) {

        try(FileInputStream fileInputStream = new FileInputStream("d:\\test.txt")) {

            int b = 0;

            while (true) {

                b = fileInputStream.read();

                if (b == -1) {
                    break;
                }

                System.out.println((char)b);
            }
        } catch (IOException e) {

        }


    }

}

/**
 * InputStream方法
 * int read() 从输入流读取一个8位的字节，把他转换为0~255之间的整数，并返回这一整数
 * int read(byte[] b) 从输入流读取若干字节，把它们保存到参数b指定的字节数组中，返回的整数表示读取字节数
 * int read(byte[] b, int off, int len) 从输入流读取若干字节，把它们保存带参数b指定的字节数组中，off指定字节数组开始保存数据的起始下标，len表示读取字节数
 * void close() 关闭此输入流并释放与该流关联的所有系统资源
 *
 * OutputStream方法
 * void write(int b) 向输出流写入一个字节
 * void write(byte[] b) 把参数b指定的字节数组的所有字节写到输出流
 * void write(byte[] b, int off, int len) 将指定byte数组中从偏移量off开始的len字节写入输出流
 * void flush() 刷新此输出流并强制写出所有缓冲的输出字节
 * void close() 关闭此输出流并释放与此流相关的所有系统资源
 */
/**
 *                                                       InputStream
 * ByteArrayInputStream FileInputStream   FilterInputStream    PipedInputStream   SequenceInputStream    ObjectInputStream
 *                                  BufferInputStream DataInputStream
 *
 *                                                       OutputStream
 * ByteArrayOutputStream FileOutputStream  FilterOutputStream  PipedOutputStream ObjectInputStream
 *                                  BufferOutputStream  PrintStream  DataOutputStream
 */
