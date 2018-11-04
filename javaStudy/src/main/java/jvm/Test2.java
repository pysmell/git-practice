package jvm;

/**
 * 大对象直接进入老年代
 * 1.7jdk默认垃圾收集器为   Parallel Scavenge（新生代）+Parallel Old（老年代） ，
 * PretenureSizeThreshol在jdk1.7未指定垃圾收集器时未生效
 * PretenureSizeThreshol参数只对Serial和ParNew两款收集器有效，ParallelScavenge收集器不认识这个参数
 * ParallelScavenge收集器一般不需要设置。如果遇到必须使用此参数的场合，可以考虑ParNew加CMS的收集器组合
 */
public class Test2 {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        testPretenureSizeThreshold();

    }

    public static void testPretenureSizeThreshold() {
        byte[] allocation;

        //直接分配在老年代中
        allocation = new byte[4 * _1MB];
    }

}
