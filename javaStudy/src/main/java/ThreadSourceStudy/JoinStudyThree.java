package ThreadSourceStudy;

public class JoinStudyThree extends Thread {

    public static void main(String[] args) throws InterruptedException {

        JoinStudyThree joinStudyTwo = new JoinStudyThree();
        joinStudyTwo.start();
        //调用先获得joinStudyTwo锁对象，由锁对象调用wait()方法，会释放掉锁对象，加入到此锁对象的等待池中
        //等等待时间过期后，该等待池中的线程就被放到了锁池中，该线程从锁池中获得机锁，然后回到wait()前的中断现场。
        joinStudyTwo.join(1000);
        System.out.println("main end");
    }

    @Override
    public void run() {
        synchronized (this) {

            System.out.println("subThread begin");
            try {
                System.out.println("subThread sleep begin");
                Thread.sleep(2000);
                System.out.println("subThread sleep end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("subThread end");
        }
    }
}
