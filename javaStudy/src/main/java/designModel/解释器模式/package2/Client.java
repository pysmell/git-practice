package designModel.解释器模式.package2;

public class Client {

    public static void main(String[] args) {

        Context ctx = new Context();
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Constant c = new Constant(true);
        ctx.assign(x, false);
        ctx.assign(y, true);

        System.out.println(new And(x, c).interpret(ctx));

    }

}
/**
 * 解释器模式
 *
 */