package BigDecimalPackage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * BigInteger转换成BigDecimal
 */
public class BigDecimalTest7 {

    public static void main(String[] args) {

        BigInteger bigInteger = new BigInteger("1000");
        BigDecimal bigDecimal = new BigDecimal(bigInteger);
        System.out.println(bigDecimal.intValue());

        BigInteger bigInteger1 = new BigInteger("-1000");
        BigDecimal bigDecimal1 = new BigDecimal(bigInteger1);
        System.out.println(bigDecimal1.intValue());

        //BigDecimal的值等于BigInteger的值乘于10^-scale
        BigInteger bigInteger3 = new BigInteger("1000");
        BigDecimal bigDecimal3 = new BigDecimal(bigInteger3, 1);
        System.out.println(bigDecimal3.intValue());

        BigInteger bigInteger4 = new BigInteger("1000");
        BigDecimal bigDecimal4 = new BigDecimal(bigInteger4, -1);
        System.out.println(bigDecimal4.intValue());

        BigInteger bigInteger5 = new BigInteger("-1000");
        BigDecimal bigDecimal5 = new BigDecimal(bigInteger5, 1);
        System.out.println(bigDecimal5.intValue());

        BigInteger bigInteger6 = new BigInteger("-1000");
        BigDecimal bigDecimal6 = new BigDecimal(bigInteger6, -1);
        System.out.println(bigDecimal6.intValue());

        BigInteger bigInteger7 = new BigInteger("-1000");
        BigDecimal bigDecimal7 = new BigDecimal(bigInteger7, MathContext.DECIMAL32);
        System.out.println(bigDecimal7.doubleValue());

        //实测RoundingMode
        BigInteger bigInteger8 = new BigInteger("1000");
        BigDecimal bigDecimal8 = new BigDecimal(bigInteger8, new MathContext(0, RoundingMode.HALF_UP));
        System.out.println(bigDecimal8.doubleValue());

        BigDecimal bigDecimal9 = new BigDecimal("100.5", new MathContext(2, RoundingMode.HALF_UP));
        System.out.println(bigDecimal9.doubleValue());
    }

}
/**
 BigDecimal(BigInteger val)
 将 BigInteger转换成 BigDecimal 。
 BigDecimal(BigInteger unscaledVal, int scale)
 将BigInteger的 BigInteger值和 int等级转换为 BigDecimal 。
 BigDecimal(BigInteger unscaledVal, int scale, MathContext mc)
 将 BigInteger未缩放值和 int扩展转换为 BigDecimal ，根据上下文设置进行舍入。
 BigDecimal(BigInteger val, MathContext mc)
 根据上下文设置将 BigInteger转换为 BigDecimal舍入。
 */