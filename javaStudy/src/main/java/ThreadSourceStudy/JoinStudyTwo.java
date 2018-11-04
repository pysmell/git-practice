package ThreadSourceStudy;

public class JoinStudyTwo extends Thread {

    public static void main(String[] args) throws InterruptedException {

        JoinStudyTwo joinStudyTwo = new JoinStudyTwo();
        joinStudyTwo.start();
        joinStudyTwo.join(1000);
        System.out.println("main end");
    }

    @Override
    public void run() {

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
