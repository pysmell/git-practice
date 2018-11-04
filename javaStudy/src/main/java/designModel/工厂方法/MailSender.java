package designModel.工厂方法;

public class MailSender implements Sender {
    @Override
    public void send() {
        System.out.println("MailSender send");
    }
}
