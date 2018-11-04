package designModel.享元模式.font;

import java.util.Hashtable;

public class FontInnerFactory {

    private Hashtable<String, FontInner> charHashTable = new Hashtable<>();

    public FontInnerFactory() {
    }

    public FontInner getFlyWeight(String fontString, String  status) {
        if (charHashTable.get(fontString) != null) {
            return charHashTable.get(fontString);
        } else {
            FontInner fontInner = new FontInner(fontString, status);
            charHashTable.put(fontString, fontInner);
            return fontInner;
        }
    }

    public Hashtable getCharHashTable() {
        return charHashTable;
    }
}
