package designModel.工厂方法;

public class MailSenderFactory implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
