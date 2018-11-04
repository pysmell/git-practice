package designModel.状态模式;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Context {

    private State state;

    public void method() {

        if (state.getValue().equals("state1")) {
            state.method1();
        } else if(state.getValue().equals("state2")) {
            state.method2();
        }

    }


}
