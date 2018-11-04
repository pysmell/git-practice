package designModel.工厂方法;

public class SmslSenderFactory implements Provider {

    @Override
    public Sender produce() {
        return new SmsSender();
    }
}
