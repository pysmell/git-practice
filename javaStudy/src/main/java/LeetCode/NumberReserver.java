package LeetCode;

/**
 * 给定一个 32 位有符号整数，将整数中的数字进行反转,假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 */
public class NumberReserver {

    public static void main(String[] args) {
        System.out.println(new NumberReserver().reverse(1534236469));
    }


    public int reverse(int x) {

        StringBuilder result = new StringBuilder();

        int num = 0;

        double temp = 0;

        if ((x+"").length()==1) {
            return x;
        }

        String str = x+"";

        char[] chars = str.toCharArray();

        for (int i = chars.length-1; i >= 0; i--) {

            if (i==0) {
                if (chars[i] == '-') {
                    result.insert(0, chars[i]);
                    break;
                } else {
                    result.append(chars[i]);
                    break;
                }
            }

            result.append(chars[i]);

        }

        String string = result.toString();

        temp = Double.valueOf(string);

        if ((Math.pow(2, 31)-1) < temp || temp < Math.pow(-2, 31)) {
            return num;
        }

        num = (int)temp;

        return num;
    }

}
