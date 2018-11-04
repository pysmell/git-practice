package designModel.解释器模式.package1;

public class Test {

    /**
     * 计算9+2-8
     */
    public static void main(String[] args) {

        Integer result = new Plus().interpret(new Context(new Minus().interpret(new Context(9, 2)), 8));

        System.out.println(result);
    }

}
