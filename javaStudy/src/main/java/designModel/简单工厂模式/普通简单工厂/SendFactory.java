package designModel.简单工厂模式.普通简单工厂;

public class SendFactory {

    public Sender produce(String type) {
        Sender sender = null;
        if ("sms".equals(type)) {
            sender = new SmsSender();
        } else if ("mail".equals(type)) {
            sender = new MailSender();
        }
        return sender;
    }

}
