package BigDecimalPackage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BigDecimalTest1 {

    public static void main(String[] args) {

        BigDecimal bg1, bg2, bg3;

        bg1 = new BigDecimal("123.125");

        bg2 = bg1.setScale(6);

        //如果设置scale小于原先的精度，如果不指定执行的策略，会报错
        bg3 = bg1.setScale(2, BigDecimal.ROUND_HALF_DOWN);

        String str = "The value of " +bg1+ " after changing the scale to 6 is " + bg2 + " after changing the scale to -6 is " + bg3;

        // print bg2,bg3 value
        System.out.println( str );
        List<String> stringList = new ArrayList<>();
        stringList.add("1");
        stringList.add("3");
        List<String> stringList1 = new ArrayList<>();
        stringList1.add("1");
        stringList1.add("2");
        stringList.removeAll(stringList1);
        for (String string : stringList) {
            System.out.println(string);
        }

    }

}
