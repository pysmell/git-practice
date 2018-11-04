package BigDecimalPackage;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * BigDecimal类提供了算术，缩放操作，舍入，比较，散列和格式转换的操作
 */
public class BigDecimalTest {

    public static void main(String[] args) {

        BigInteger bigInteger = new BigInteger("100");

        BigDecimal bigDecimal = new BigDecimal(bigInteger,0);

        System.out.println(bigDecimal.intValue());

    }


}
/**
 * BigDecimal
 * 构造函数
 * BigDecimal(BigInteger val)
 *      将 BigInteger转换成 BigDecimal 。
 *1.public BigDecimal(double val)    将double表示形式转换为BigDecimal 不建议使用
 *2.public BigDecimal(int val)　　将int表示形式转换成BigDecimal
 *3.public BigDecimal(String val)　　将String表示形式转换成BigDecimal
 * 属性
 * 方法
 */

























