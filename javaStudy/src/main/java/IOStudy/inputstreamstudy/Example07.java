package IOStudy.inputstreamstudy;

import java.io.FileReader;
import java.io.IOException;

/**
 * 字符流
 */
public class Example07 {

    public static void main(String[] args) {

        try(FileReader fileReader = new FileReader("d:\\test.txt")){

            int ch;

            while ((ch = fileReader.read())!=-1) {
                System.out.println((char)ch);
            }

        }catch (IOException e){

        }

    }

}
