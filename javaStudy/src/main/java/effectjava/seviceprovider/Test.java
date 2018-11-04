package effectjava.seviceprovider;

/**
 * 客户端测试类
 * @author linqw
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("effectjava.seviceprovider.SubwayByDisposableProviderImpl");
        Class.forName("effectjava.seviceprovider.SubwayByEasyCardProviderImpl");

        SubWayInterface subWayInterface = ServiceManager.getService("disposable");
        subWayInterface.in();
        subWayInterface.out();

        SubWayInterface subWayInterface1 = ServiceManager.getService("easyCardService");
        subWayInterface1.in();
        subWayInterface1.out();

    }

}

























