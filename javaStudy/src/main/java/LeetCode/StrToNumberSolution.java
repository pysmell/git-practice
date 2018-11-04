package LeetCode;
/**
 * 实现 atoi，将字符串转为整数。
 在找到第一个非空字符之前，需要移除掉字符串中的空格字符。如果第一个非空字符是正号或负号，选取该符号，
 并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。如果第一个非空字符是数字，
 则直接将其与之后连续的数字字符组合起来，形成整数。字符串可以在形成整数的字符后面包括多余的字符，
 这些字符可以被忽略，它们对于函数没有影响。当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；
 或字符串仅包含空白字符时，则不进行转换。若函数不能执行有效的转换，返回 0。
 */
public class StrToNumberSolution {

    public static void main(String[] args) {
        System.out.println(new StrToNumberSolution().myAtoi("-   234"));
    }

    public int myAtoi(String str) {

        String result = "";

        int num = 0;

        double temp = 0;

        String initStr = "+-0123456789 ";

        if (str == null || str.equals("") || str.equals("+") || str.equals("-")) {

            return num;

        }

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            if (!(initStr.indexOf(chars[i])>=0)) {

                if (result.equals("") || result.equals("+") || result.equals("-")) {

                    return num;

                } else {

                    temp = Double.valueOf(result);

                    num = temp < ((int)Math.pow(-2,31))?((int)Math.pow(-2,31)):(int)temp;

                    num = ((int)Math.pow(2,31))<temp?((int)Math.pow(2,31)):(int)temp;

                    return num;
                }

            }

            if (chars[i] == ' ') {

                if (result.equals("")) {

                    continue;

                } else {
                    break;
                }

            }
            if (chars[i] == '+' || chars[i] == '-') {

                if (result.equals("+") || result.equals("-")) {

                    return num;

                } else {

                    if (result.equals("")) {

                        result += chars[i];
                        continue;
                    } else {
                        temp = Double.valueOf(result);

                        num = temp < ((int)Math.pow(-2,31))?((int)Math.pow(-2,31)):(int)temp;

                        num = ((int)Math.pow(2,31))<temp?((int)Math.pow(2,31)):(int)temp;

                        return num;
                    }

                }

            }

            result += chars[i];

        }

        if (result.equals("") || result.equals("+") || result.equals("-")) {
            return num;
        }

        temp = Double.valueOf(result);

        num = temp < ((int)Math.pow(-2,31))?((int)Math.pow(-2,31)):(int)temp;

        num = ((int)Math.pow(2,31))<temp?((int)Math.pow(2,31)):(int)temp;

        return num;

    }

}
