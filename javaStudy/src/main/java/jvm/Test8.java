package jvm;

import java.util.ArrayList;
import java.util.List;

public class Test8 {

    public static void main(String[] args) throws InterruptedException {

        fillHeap(1000);

    }

    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        //老年代对象还没有释放，因为gc还在这个方法内，想要全部释放掉，需要将其gc放在fillHeap方法外面
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }
}






















