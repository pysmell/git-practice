package jvm;

/**
 * 可以用cmd java-version 查看 jvm是server 还是client server的话在gc前会先判断目前要分配的对象
 * 是否大于等于eden的一半，如果是将其分配在老年区中
 * 为了能更好的适应不同程序的内存状况，虚拟机并不总是要求对象的年龄必须达到MaxTenuringThreshold才能晋升老年代，
 * 如果在Survivor空间中相同年龄所有对象大小的总和大于空间Survivor空间的一半，年龄大于或等于
 * 该年龄的对象就可以直接进入老年代，无须等到MaxTenuringThreshold中要求的年龄
 */
public class Test6 {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        testTenuringThreshold();

    }

    public static void testTenuringThreshold() {

        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;

        allocation1 = new byte[_1MB / 2];

        allocation2 = new byte[_1MB];

        //直接分配到老年区中
        allocation3 = new byte[_1MB * 7];
        //会导致年轻区gc，这个会将其分配到eden中，eden中的allocation1，allocation2移到from survivor中
        allocation4 = new byte[_1MB * 7];

        //allocation5 = new byte[_1MB / 2];

        //System.gc();
    }

}
