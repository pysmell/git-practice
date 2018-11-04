package effectjava.seviceprovider;

/**
 * 易卡通服务
 * @author linqw
 */
public class SubWayByEasyCard implements SubWayInterface {
    @Override
    public boolean in() {
        System.out.println("通过易卡通进入地铁");
        return false;
    }

    @Override
    public boolean out() {
        System.out.println("通过易卡通出地铁");
        return false;
    }
}
