package jvm;

/**
 * 对象优先在eden区分配
 */
public class Test {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        testAllocation();
    }

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        //eden区容量无法在存放此变量，出现一次Minor GC
        allocation4 = new byte[3 * _1MB];

    }

}
































