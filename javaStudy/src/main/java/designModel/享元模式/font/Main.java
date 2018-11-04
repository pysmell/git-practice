package designModel.享元模式.font;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        int[] size = {8, 9, 10, 11, 12};
        String[] color = {"FFFFFF", "000000", "FF00FF", "CCCCCC", "111111"};
        FontInnerFactory fontInnerFactory = new FontInnerFactory();
        String aString = "Ateststring";
        List<Font> fontList = new ArrayList<>();
        for (int i = 0;i < aString.length();i++) {
            int j = 0;
            j = (int)Math.floor(Math.random()*5);
            ConCreteFont conCreteFont = new ConCreteFont(fontInnerFactory.getFlyWeight(aString.substring(i, i+1), ""));
            conCreteFont.setFont(color[j], size[j]);
            fontList.add(conCreteFont);
        }
    }

}











