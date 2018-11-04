package ThreadSourceStudy;

/**
 * join()方法源码分析
 * 等待t线程mills毫秒直到这个线程死亡，如果等待的时间到，子线程还未结束，主线程会继续执行
 * 传入0即一直等到子线程结束，才能重新运行
 */
public class JoinStudy extends Thread {

    public static void main(String[] args) {

        JoinStudy joinStudy = new JoinStudy();
        joinStudy.start();

        try {
            joinStudy.join(1000);  //里会调用wait()方法，wait方法会释放对象锁，等待时间过后重新获得对象锁
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("subThread begin");

        try {
            System.out.println("subThread sleep begin");

            Thread.sleep(900);

            System.out.println("subThread sleep end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("subThread end");
    }
}


/**
 join()源码
 public final synchronized void join(long millis)
 throws InterruptedException {
 long base = System.currentTimeMillis();
 long now = 0;

 if (millis < 0) {
 throw new IllegalArgumentException("timeout value is negative");
 }

 if (millis == 0) {
 while (isAlive()) {
 wait(0); 本地方法
 }
 } else {
 while (isAlive()) {
 long delay = millis - now;
 if (delay <= 0) {
 break;
 }
 wait(delay);
 now = System.currentTimeMillis() - base;
 }
 }
 }


 */