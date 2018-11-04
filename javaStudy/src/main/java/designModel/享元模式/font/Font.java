package designModel.享元模式.font;

/**
 * @author linqw
 * flyWeight中两个重要状态，内部状态（共享状态），外部状态（传入的状态）
 * 求同存异
 */
public interface Font {

    void setFont(String color, int size);

    void getFont();
}
