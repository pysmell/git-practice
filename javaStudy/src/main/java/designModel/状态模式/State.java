package designModel.状态模式;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class State {

    private String value;

    public void method1() {
        System.out.println("execute the first opt!");
    }

    public void method2() {
        System.out.println("execute the two opt!");
    }

}
