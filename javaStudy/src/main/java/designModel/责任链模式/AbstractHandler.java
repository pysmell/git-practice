package designModel.责任链模式;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class AbstractHandler implements Handler {

    protected Handler handler;




}
