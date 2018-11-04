import javax.management.MBeanServerFactory;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {
    public static final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    /*public static void main(String[] args) {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("1","linqw");
        map.put("0","hll");
        map.put("2","lbf");
        map.put("4","lqm");
        map.put("3","lmh");
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        String s = "0001011101111011";
        int x = 0;
        for(char c: s.toCharArray()) {
            System.out.println(c);
            x = x * 2 + (c == '1' ? 1 : 0);
        }
        System.out.println(x);

        System.out.println(new Timestamp(System.currentTimeMillis()));

        byte b = -20;
        System.out.println("---" + (b & 0xff));

        final byte b2 = -20;
        final byte mask = (byte) 0xff;
        byte b3 = b2 + mask;//由于final定义即为常量
        System.out.println(b3);
        System.out.println(b2 & mask); // -20
        int a = b2;
        System.out.println(a);
        System.out.println(Integer.toHexString(-1));
    }*/

    /**
     *
     */
     /*   public static void main(String[] args) {
            int number = 6;
            //原始数二进制
            printInfo(number);
            System.out.println(number);
            number = number << 1;
            //左移一位
            printInfo(number);
            System.out.println(number);
            number = number >> 1;
            //右移一位
            printInfo(number);
            System.out.println(number);
        }

        *//**
         * 输出一个int的二进制数
         * @param num
         *//*
        private static void printInfo(int num){
            System.out.println(Integer.toBinaryString(num));
        }*/
    /**
     * 将其十进制转成十六进制
     */
    public static String intToHex(String number) {

        StringBuilder stringBuilder = new StringBuilder();
        Integer intNumber = Integer.valueOf(number);

        int radix = 1 << 4;
        int mask = radix - 1;

        while (intNumber != 0) {
            stringBuilder.append(hexArray[intNumber & mask]);
            intNumber>>>=4;
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(Integer.toHexString(-6001));
        System.out.println(intToHex("6001"));
        System.out.println(intToHex("-6001"));
    }
}
