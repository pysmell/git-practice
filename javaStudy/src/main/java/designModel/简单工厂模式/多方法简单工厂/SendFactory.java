package designModel.简单工厂模式.多方法简单工厂;

public class SendFactory {

    public Sender produceSms() {
        return new SmsSender();
    }

    public Sender produceMail() {
        return new MailSender();
    }
}




































