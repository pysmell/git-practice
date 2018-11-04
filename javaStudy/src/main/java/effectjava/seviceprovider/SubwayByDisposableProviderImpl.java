package effectjava.seviceprovider;

/**
 * 一次性卡服务提供者
 * @author linqw
 */
public class SubwayByDisposableProviderImpl implements SubwayProviderInterface {

    static {
        SubwayProviderInterface subwayProviderInterface = new SubwayByDisposableProviderImpl();
        ServiceManager.registerProvider("disposable", subwayProviderInterface);
    }

    @Override
    public SubWayInterface getService() {
        return new SubWayDisposable();
    }
}
