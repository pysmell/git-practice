package designModel.代理模式;

public class Proxy implements Sourceable {

    private Sourceable source;

    public Proxy(Source source) {
        this.source = source;
    }

    @Override
    public void method() {
        before();
        source.method();
        after();
    }

    private void before() {
        System.out.println("before proxy");
    }

    private void after() {
        System.out.println("after proxy");
    }
}
