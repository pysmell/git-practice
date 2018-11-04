package IOStudy.inputstreamstudy;

import java.io.FileOutputStream;
import java.io.IOException;

public class Example02 {

    public static void main(String[] args) {

        try (FileOutputStream fileOutputStream = new FileOutputStream("d:\\test.txt", true)) {

            String str = "勇士vs骑士";

            byte[] bytes = str.getBytes();

            for (int i = 0; i < bytes.length; i++) {

                fileOutputStream.write(bytes[i]);

            }

        } catch (IOException e) {

        }

    }

}
