package BigDecimalPackage;

import java.math.BigDecimal;

public class BigDecimalTest3 {

    public static void main(String[] args) {

        BigDecimal intBigDecimal = new BigDecimal(2);
        BigDecimal bDouble = new BigDecimal(2.3);
        BigDecimal bString = new BigDecimal("2.3");
        System.out.println("intBigDecimal=" + intBigDecimal);
        System.out.println("bDouble=" + bDouble);
        System.out.println("bString=" + bString);

        //优先使用String构造方法，double必须用作BigDecimal的源时
        BigDecimal bDouble1 = BigDecimal.valueOf(2.3);
        BigDecimal bDouble2 = new BigDecimal(Double.toString(2.3));
        System.out.println(bDouble1);
        System.out.println(bDouble2);
    }

}
/**
 * 常用的几种构造方法
 * public BigDecimal(double val) 将double表示形式转换为BigDecimal 不建议使用，还是会出现精度问题
 * public BigDecimal(int val)　　将int表示形式转换成BigDecimal
 * public BigDecimal(String val)　　将String表示形式转换成BigDecimal
 */






































