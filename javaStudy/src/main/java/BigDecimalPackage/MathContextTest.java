package BigDecimalPackage;

import java.math.MathContext;

public class MathContextTest {

    public static void main(String[] args) {
        MathContext mathContext1 = MathContext.DECIMAL128;
        System.out.println("precision" + mathContext1.getPrecision() + " roundingMode" + mathContext1.getRoundingMode()+ " string" + mathContext1.toString());

    }

}



/**
 * MathContext 封装了描述数值运算符某些规则的上下文设置的不可变对象
 * 构造方法
 * MathContext（int setPrecision)
 * 构造一个新的MathContext具有指定的精度和HALF_UP舍入模式
 * MathContext（int setPrecision， RoundingMode setRoundingMode)
 * 构造一个新的MathContext，具有指定的精度和舍入模式
 * MathContext（String val）
 * 从一个字符串构造一个新的MathContext
 * 属性
 * static MathContext DECIMAL128
 * A MathContext对象，其精度设置与IEEE754R Decimal128格式，34位数字和HALF_EVEN的舍入模式匹配
 * static MathContext DECIMAL32
 * A MathContext对象，其精度设置与IEEE754R Decimal32格式，7位数字以及HALF_EVEN的舍入模式匹配
 * static MathContext DECIMAL64
 * A MathContext对象，其精度设置与IEEE754R Decima64格式，16位数字以及HALF_EVEN的舍入模式匹配
 * static MathContext UNLIMITED
 * A MathContext对象,其设置具有无限精度算术所需的值
 * 方法
 * boolean equals（object x）将此MathContext与指定的Object进行比较以获得相等性
 * int getPrecision（）
 * 返回precision设置
 * getRoundingMode（）
 * 返回roundingMode设置
 * int hashCode（）
 * 返回此MathContext的哈希码
 * String toString（）
 * 返回此MathContext的字符串表示MathContext
 */





























