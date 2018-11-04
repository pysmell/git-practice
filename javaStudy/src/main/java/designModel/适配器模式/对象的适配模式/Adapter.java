package designModel.适配器模式.对象的适配模式;

public class Adapter implements Targetable {

    private Source source;

    public Adapter(Source source) {
        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }
}
