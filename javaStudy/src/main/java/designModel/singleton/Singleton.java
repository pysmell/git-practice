package designModel.singleton;

public class Singleton {

    private static Singleton singleton = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    /**
     * 性能差
     */
    public static synchronized Singleton getSingleton() {
        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public static Singleton getInstance1() {
        if (singleton == null) {
            synchronized (singleton) {
                if (singleton == null) {
                    singleton = new Singleton(); //还是会有问题，假设A、B两个线程，A先进入，由于Java机制，当A对Singleton先分配内存还未进行初始化，就退出，B拿到的实例有可能会错误
                }
            }
        }
        return singleton;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return singleton;
    }
}
