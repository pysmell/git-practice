package designModel.装饰模式;

public class DecoratorTest {

    public static void main(String[] args) {

        Sourceable sourceable = new Source();

        Sourceable obj = new Decorator(sourceable);

        obj.method();
    }

}
