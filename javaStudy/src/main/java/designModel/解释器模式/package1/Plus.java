package designModel.解释器模式.package1;

public class Plus implements Expression {

    @Override
    public Integer interpret(Context context) {
        return context.getNum1() - context.getNum2();
    }
}
