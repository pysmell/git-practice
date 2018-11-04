package IOStudy.inputstreamstudy;

import java.io.*;

/**
 * 字节缓冲流
 */
public class Example05 {

    public static void main(String[] args) {

        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("d:\\test.txt")); BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("d:\\test2.txt"))) {

            int len;

            while ((len = inputStream.read())!=-1) {
                bufferedOutputStream.write(len);
            }

        } catch (IOException e) {

        }
    }

}
