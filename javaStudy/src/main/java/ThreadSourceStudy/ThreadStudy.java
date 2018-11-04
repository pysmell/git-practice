package ThreadSourceStudy;

/**
 * Thread源码分析
 */
public class ThreadStudy extends Thread {

    public static void main(String[] args) {

        ThreadStudy t = new ThreadStudy();

        t.start();

        try {
            t.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        System.out.println("subThread begin");
        try {
            System.out.println("subThread Thread begin");
            Thread.sleep(900);
            System.out.println("subThread Thread end");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("subThread end");
    }
}

/*
* sleep(long mills, int nanos) 源码分析
*   最后还是调用sleep(long millis) 这个本地方法
*     public static void sleep(long millis, int nanos)
    throws InterruptedException {
        if (millis < 0) {  如果毫秒小于0抛出异常
            throw new IllegalArgumentException("timeout value is negative");
        }
        nanos只能在0-999999之间
        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                                "nanosecond timeout value out of range");
        }
        如果nanos大于500000或者（不等于0并且毫秒等于0）毫秒加1
        if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
            millis++;
        }
        sleep(millis);
    }
* */
/*
* run方法是如何被调用的
* 开启线程时候不是直接去调用run()方法，而是调用start()方法
* start()方法主要是调用了一个start0()这个方法
* start0()方法会自动调用对象的run方法
*
* start()源码分析  将启动的线程加入group中
*     public synchronized void start() {
        if (threadStatus != 0)
                throw new IllegalThreadStateException();

                group.add(this);

                boolean started = false;
                try {
                start0();  //本地方法
                started = true;
                } finally {
                try {
                if (!started) {
                group.threadStartFailed(this);
                }
                } catch (Throwable ignore) {
                }
                }
     }
* */

/*
* run()源码分析
* @Override
    public void run() {
        if (target != null) {
            target.run();//target是一个Runnable的引用
        }
    }
    从源码中可以看出，如果target存在，则执行target的run方法，否则什么也不做。
    也就是说Thread的run()方法总是先被调用，然后调用target（构造函数中的Runnable对象）的run()方法。
* */




































