package designModel.简单工厂模式.静态方法简单工厂;

public class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("MailSender send");
    }
}
