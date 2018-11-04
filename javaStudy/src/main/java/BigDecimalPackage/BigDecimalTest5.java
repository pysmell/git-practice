package BigDecimalPackage;

import java.math.BigDecimal;

/**
 * 减乘除其实最终都返回的是一个新的BigDecimal对象，
 * 因为BigInteger与BigDecimal都是不可变的（immutable）的，
 * 在进行每一步运算时，都会产生一个新的对象
 */
public class BigDecimalTest5 {

    public static void main(String[] args) {

        BigDecimal a = new BigDecimal("4.5");
        BigDecimal b = new BigDecimal("1.5");
        a.add(b);

        //输出4.5. 加减乘除方法会返回一个新的BigDecimal对象，原来的a不变
        System.out.println(a);

    }

}
