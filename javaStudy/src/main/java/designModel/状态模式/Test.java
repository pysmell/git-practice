package designModel.状态模式;

public class Test {

    public static void main(String[] args) {

        State state = new State("state1");

        Context context = new Context(state);

        context.method();

        state.setValue("state2");

        context.method();

    }


}
