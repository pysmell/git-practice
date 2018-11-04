package time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest {

    public static void main(String[] args) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //获取当前时间
        Calendar ca=Calendar.getInstance();

        ca.add(Calendar.MONTH, -11);

        System.out.println(dateFormat.format(ca.getTime()));

        System.out.println(ca.getTime());
    }

}
