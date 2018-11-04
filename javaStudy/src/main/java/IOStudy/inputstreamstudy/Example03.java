package IOStudy.inputstreamstudy;

import sun.misc.PostVMInitHook;

import java.io.*;

public class Example03 {

    public static void main(String[] args) {

        try(InputStream inputStream = new FileInputStream("D:\\test.txt"); OutputStream outputStream = new FileOutputStream("D:\\testTarget.txt")) {

            int len;

            while((len = inputStream.read()) != -1) {

                outputStream.write(len);

            }

        } catch (IOException e) {

        }
    }

}
