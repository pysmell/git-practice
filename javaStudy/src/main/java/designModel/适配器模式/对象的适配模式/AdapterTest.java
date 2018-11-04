package designModel.适配器模式.对象的适配模式;

public class AdapterTest {

    public static void main(String[] args) {
        Source source = new Source();
        Targetable targetable = new Adapter(source);
        targetable.method1();
        targetable.method2();
    }

}
