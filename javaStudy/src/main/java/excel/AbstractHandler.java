package excel;

import lombok.Getter;
import lombok.Setter;

/**
 * @author admin
 */
@Setter
@Getter
public abstract class AbstractHandler implements Handler {

    /**
     * 下一个Handler
     */
    protected Handler handler;

    /**
     * 请求处理标志
     */
    protected int resolveFlag;
}
