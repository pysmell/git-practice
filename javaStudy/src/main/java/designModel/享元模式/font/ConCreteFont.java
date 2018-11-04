package designModel.享元模式.font;

/**
 * 完整的字体，包括内容（共享），颜色，字体大小
 */
public class ConCreteFont implements Font {

    private String color;
    private int size;
    private FontInner inner;

    public ConCreteFont(FontInner s) {
        inner = s;
    }

    @Override
    public void setFont(String color, int size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public void getFont() {
        System.out.println("String :" + inner.getFontString() + "--- color is:"
                + color + "--- size is:" + size);
    }
}
