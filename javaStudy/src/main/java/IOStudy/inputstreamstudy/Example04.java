package IOStudy.inputstreamstudy;

import java.io.*;

/**
 * 字节流的缓冲区
 */
public class Example04 {

    public static void main(String[] args) {

        try(InputStream inputStream = new FileInputStream("D:\\test.txt"); OutputStream outputStream = new FileOutputStream("D:\\test2.txt")) {

            byte[] bytes = new byte[1024];

            int len;

            while ((len = inputStream.read(bytes))!=-1) {

                outputStream.write(bytes, 0, len);

            }

        }catch (IOException e) {

        }


    }

}
