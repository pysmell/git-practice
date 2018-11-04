package effectjava.seviceprovider;

/**
 * 易卡通服务提供者
 * @author linqw
 */
public class SubwayByEasyCardProviderImpl implements SubwayProviderInterface {

    static {
        SubwayProviderInterface subwayProviderInterface = new SubwayByEasyCardProviderImpl();
        ServiceManager.registerProvider("easyCardService", subwayProviderInterface);
    }

    @Override
    public SubWayInterface getService() {
        return new SubWayByEasyCard();
    }
}
