package jvm;

public class Test5 {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        testTenuringThreshold();
    }

    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {

        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;

        allocation1 = new byte[_1MB / 4];

        allocation5 = new byte[_1MB / 4];

        allocation2 = new byte[3 * _1MB];

        allocation3 = new byte[3 * _1MB];

        //这里会发生GC,将其上面的对象移到老年区中
        allocation4 = new byte[2 * _1MB];


    }

}
