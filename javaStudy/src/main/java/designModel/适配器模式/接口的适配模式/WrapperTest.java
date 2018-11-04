package designModel.适配器模式.接口的适配模式;

/**
 * 由于接口中的所有方法不一定都是我们所需要的，可以用一个抽象类实现此接口，做适配
 */
public class WrapperTest {

    public static void main(String[] args) {

        Sourceable sourceable1 = new SourceSub1();
        Sourceable sourceable2 = new SourceSub2();
        sourceable1.method1();
        sourceable2.method2();
    }

}
/**
 * 三种适配模式的使用场景
 * 类的适配模式
 *      希望将一个原有的类转换成满足另一个接口的类时，可以使用此模式
 *      创建一个新类，继承原有的类，实现新的接口
 * 对象的适配模式
 *      希望将一个对象转换成满足另一个新接口的对象时，可以创建一个Wrapper类
 *      可以创建一个Wrapper类，持有原类的一个实例，在Wrapper类的方法中，调用实例的方法
 * 接口的适配模式
 *      当不希望实现一个接口中所有方法
 */














