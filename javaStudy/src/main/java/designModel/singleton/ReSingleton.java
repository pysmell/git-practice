package designModel.singleton;

/**
 * 内部类创建单例
 */
public class ReSingleton {

    private ReSingleton() {

    }

    /**
     * 类的加载过程是互斥
     */
    private static class SingletonFactory {
        private static ReSingleton singleton = new ReSingleton();
    }

    public static ReSingleton getInstance() {
        return SingletonFactory.singleton;
    }

}
