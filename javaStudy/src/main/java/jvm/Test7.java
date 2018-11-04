package jvm;

/**
 * 空间分配担保
 */
public class Test7 {

    private static final int _1MB = 1024 * 1024;

    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        //为什么上面from space也会有值
    }

    public static void main(String[] args) {
        testHandlePromotion();
    }

}
