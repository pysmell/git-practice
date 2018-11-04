package BigDecimalPackage;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalTest6 {

    public static void main(String[] args) {

        BigDecimal a = new BigDecimal("4.5");
        BigDecimal b = new BigDecimal("13");

        a.setScale(5, RoundingMode.HALF_UP);
        //scale表示保留小数后几位
        System.out.println(a.divide(b, 3, RoundingMode.HALF_UP));
        //setPrecision表示所有保留几位数（包括小数点前的数，如果是0.231312，则表示小数点后的位数）
        System.out.println(a.divide(b, new MathContext(4, RoundingMode.HALF_UP)));
        System.out.println(a.divide(b, MathContext.DECIMAL32));

    }

}
