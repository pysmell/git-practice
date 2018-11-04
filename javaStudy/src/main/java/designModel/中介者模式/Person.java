package designModel.中介者模式;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 人物
 */
@AllArgsConstructor
@Getter
@Setter
public abstract class Person {

    protected Mediator mediator;

    protected String name;

    public abstract void sendMessage(String message);

    public abstract void getMessage(String message);

}
































