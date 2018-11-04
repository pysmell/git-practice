package ThreadSourceStudy;

public class ThreadA {

    public static void main(String[] args) throws InterruptedException {

        ThreadB threadB = new ThreadB();

        threadB.start();

        synchronized (threadB) {
            System.out.println("等待对象b完成计算");
            threadB.wait(); //释放锁,其他对象可以访问线程threadB
            System.out.println("total: " + threadB.total);
        }
    }

}
//每个对象都有个机锁来控制同步访问
//一个线程结束的标志是：run()方法结束。
//一个机锁被释放的标志是：synchronized块或方法结束。
//sleep()方法的理解
//由于sleep()方法是Thread 类的方法，因此它不能改变对象的机锁。
// 所以当在一个Synchronized方法中调用sleep（）时，线程虽然休眠了，但是对象的机锁没有被释放，
// 其他线程仍然无法访问这个对象。
//wait()方法的理解
// Wait()方法和notify()方法：当一个线程执行到wait()方法时，
// 它就进入到一个和该对象相关的等待池中，同时失去了对象的机锁。当它被一个notify()方法唤醒时，
// 等待池中的线程就被放到了锁池中。该线程从锁池中获得机锁，然后回到wait()前的中断现场。
