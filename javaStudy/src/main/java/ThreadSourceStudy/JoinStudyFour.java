package ThreadSourceStudy;

public class JoinStudyFour extends Thread {

    public static void main(String[] args) throws InterruptedException {

        JoinStudyFour joinStudyFour = new JoinStudyFour();

        SubThread subThread = new SubThread(joinStudyFour);

        joinStudyFour.start();

        subThread.start();

        joinStudyFour.join(1000);

        System.out.println("main end");
    }

    @Override
    public void run() {

        System.out.println("JoinStudyFour begin");

        try {
            System.out.println("JoinStudyFour sleep begin");
            Thread.sleep(2000);
            System.out.println("JoinStudyFour sleep end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class SubThread extends Thread {

    Thread thread;

    public SubThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        holdThreadLock(thread);
    }

    private void holdThreadLock(Thread thread2) {

        synchronized (thread2) {
            System.out.println("hold Thread lock");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("release Thread lock");
        }
    }
}

















