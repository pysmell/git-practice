package BigDecimalPackage;

import java.math.BigDecimal;

import static java.math.BigDecimal.ROUND_CEILING;
import static java.math.BigDecimal.ROUND_FLOOR;
import static java.math.BigDecimal.ROUND_UNNECESSARY;

/**
 * BigDecimal加减乘除运算,都会返回一个新的对象回来
 */
public class BigDecimalTest4 {

    public static void main(String[] args) {

        BigDecimal a = new BigDecimal("-4.5");
        BigDecimal b = new BigDecimal("1.533");
        System.out.println("a + b=" + a.add(b));
        System.out.println("a - b=" + a.subtract(b));
        System.out.println("a * b=" + a.multiply(b));
        System.out.println("a / b=" + a.divide(b, 2, ROUND_FLOOR ));
    }

}
/**
 public BigDecimal add(BigDecimal value);                        //加法
 public BigDecimal subtract(BigDecimal value);                   //减法
 public BigDecimal multiply(BigDecimal value);                   //乘法
 public BigDecimal divide(BigDecimal value);                     //除法
 */
/**
 * BigDecimal除法可能出现不能整除的情况，比如4.5/1.3,这时会报错
 * public BigDecimal divide(BigDecimal value， int scale， int roundingMode) 可以传入三个参数
 * 第一个参数表示除数，第二个参数表示小数点后保留位数
 * 第三个参数表示舍入模式，只有在做除法运算或四舍五入时才用到舍入模式
 * ROUND_CEILING //向正无穷舍入,正数在其保留的小数位数的后一位不管是否大于5，全部将其进位，负数在其保留的小数位数的后一位不管是否大于5，全部将其舍掉
 * 如2.9354-》2.936， -2.935 -》-2.93
 * ROUND_DOWN //向零方向舍入
 * ROUND_FLOOR //向负无穷方向舍入
 * ROUND_HALF_DOWN //向距离最近的一边舍入，除非两边（的距离）是相等，如果是这样，向下舍入，例如1.55保留一位小数结果为1.5f
 * ROUND_HALF_EVEN //向距离最近的一边舍入，除非两边（的距离）是相等，如果是这样，如果保留位数是奇数，使用ROUND_HALF_UP,如果是
 * 偶数，使用ROUND_HALF_DOWN
 * ROUND_HALF_UP //向距离最近的一边舍入，除非两边（的距离）是相等，如果是这样，向上舍入，1.55保留一位小数结果为1.6
 * ROUND_UNNECESSARY //计算结果是精确的，不需要舍入模式
 * ROUND_UP //向远离0的方向舍入
 */










































