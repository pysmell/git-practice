package designModel.策略模式;

public class Test {

    public static void main(String[] args) {

        String exp = "2+8";

        ICalculator calculator = new Plus();

        int result = calculator.calculate(exp);

        System.out.println(result);
    }

}
