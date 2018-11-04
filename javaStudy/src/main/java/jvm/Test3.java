package jvm;

/**
 * 长期存活的对象进入老年代
 */
public class Test3 {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        testTenuringThreshold();
    }

    @SuppressWarnings("unused")
    public static void testTenuringThreshold() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5;
        allocation1 = new byte[_1MB / 4];
        allocation2 = new byte[_1MB / 4];
        //大于eden的一半，会直接分配到老年区
        allocation3 = new byte[7 * _1MB];
        //老年区无法存放，会触发一次gc，将其eden存活的对象移到from survivor
        allocation4 = new byte[7 * _1MB];

        //allocation5 = new byte[_1MB];
    }

}
/**
 * 1、长期存活的对象将进入老年代。Eden区中的对象在一次Minor GC后没有被回收，则对象年龄+1，
 * 当对象年龄达到“-XX:MaxTenuringThreshold”设置的值的时候，对象就会被晋升到老年代中。
 *
 * 2、Survivor空间中相同年龄的所有对象大小总和大于Survivor空间的一半，
 * 年龄大于或等于该年龄的对象就可以直接进入老年代，无须等到“-XX:MaxTenuringThreshold”设置要求的年龄。
 *
 */
/**
 * 在Client模式下，最后分配的4M在新生代中，先分配的6M在老年代中；在Server模式下，最后分配的4M在老年代中，先分配的6M在新生代中
 *
 * server模式下，前面都一样，但是在GC的时候有一点区别。在GC前还会进行一次判断，如果要分配的内存>=Eden区大小的一半，那么会直接把要分配的内存放入老年代中。
 *
 *
 */
