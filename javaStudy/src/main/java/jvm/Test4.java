package jvm;

public class Test4 {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        testTenuringThreshold();
    }

    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[_1MB / 4];
        //大于eden的一半，会直接分配到老年区
        allocation2 = new byte[4 * _1MB];

        //要发生gc前会做一次判断，分配的大小是否大于等于eden的一半，如果是就将其分配到old中
        allocation3 = new byte[4 * _1MB];

        allocation3 = null;

        allocation3 = new byte[4 * _1MB];
    }


}
