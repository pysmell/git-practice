package designModel.代理模式;

public class ProxyTest {

    public static void main(String[] args) {

        Source source = new Source();

        Proxy proxy = new Proxy(source);

        proxy.method();


    }

}
