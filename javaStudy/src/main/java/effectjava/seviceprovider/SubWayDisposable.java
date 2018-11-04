package effectjava.seviceprovider;

/**
 * 一次性卡服务
 * @author linqw
 */
public class SubWayDisposable implements SubWayInterface {

    @Override
    public boolean in() {
        System.out.println("通过一次性卡进入地铁");
        return false;
    }

    @Override
    public boolean out() {
        System.out.println("通过一次性卡出地铁");
        return false;
    }
}
