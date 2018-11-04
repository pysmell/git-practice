package designModel.享元模式.font;

import lombok.Getter;
import lombok.Setter;

/**
 * 字对象,包括字的内容，字的状态
 * @author linqw
 */
@Getter
@Setter
public class FontInner {

    private String fontString;

    private String status;

    public FontInner(String fontString, String status) {
        this.fontString = fontString;
        this.status = status;
    }
}
