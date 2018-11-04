package designModel.模板方法模式;

/**
 * 一个主方法，n个方法（可以有子类继承）
 */
public abstract class AbstractCalculator {

    public final int calculate(String exp, String opt) {
        int[] array = split(exp, opt);
        return calculate(array[0], array[1]);
    }

    /**
     * 被子类重写的方法
     */
    public abstract int calculate(int num1, int num2);

    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int[] arrayInt = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }

}










































