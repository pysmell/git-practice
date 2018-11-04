package designModel.适配器模式.类的适配器模式;

/**
 * 有一个Source类，拥有一个方法，待适配，目标接口是Targetable，
 * 通过Adapter类，将Source的功能扩展到Targetable里
 */
public class AdapterTest {

    public static void main(String[] args) {
        Targetable targetable = new Adapter();
        targetable.method1();
        targetable.method2();
    }

}
