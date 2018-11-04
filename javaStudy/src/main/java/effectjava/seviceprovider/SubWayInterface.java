package effectjava.seviceprovider;

/**
 * 进出地铁的服务提供者
 * @author linqw
 */
public interface SubWayInterface {

    boolean in();

    boolean out();

}
/**
 * 场景
 * 以厦门地铁进出控制为例：现在厦门地铁进出都是刷卡，有两种卡：1、一卡通（比如一次性充值50元，出地铁刷一次，扣2元）
 * 2、一次性卡（进地铁刷一次，出地铁插入回收），这两种卡都可以实现进出地铁功能，单实现的具体方法是有区别的
 * ：一卡通需要获取这卡余额，然后扣掉2元，如果余额不足2元怎么处理，一次性卡则没必要
 */
















