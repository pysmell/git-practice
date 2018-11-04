package designModel.工厂方法;

public class Test {
    public static void main(String[] args) {
        Provider provider = new SmslSenderFactory();
        Sender sender = provider.produce();
        sender.send();
    }
}
