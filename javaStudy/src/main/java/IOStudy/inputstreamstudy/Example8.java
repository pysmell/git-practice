package IOStudy.inputstreamstudy;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 写
 */
public class Example8 {

    public static void main(String[] args) {

        try(FileWriter fileWriter = new FileWriter("D:\\test.txt")) {

            String str = "你好，传智博客";

            /*fileWriter.write();*/

        } catch (IOException e){

        }

    }

}
