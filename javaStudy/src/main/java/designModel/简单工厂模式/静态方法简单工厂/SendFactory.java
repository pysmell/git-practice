package designModel.简单工厂模式.静态方法简单工厂;

/**
 * 不符合开闭原则
 */
public class SendFactory {

    public static Sender produceSms() {
        return new SmsSender();
    }

    public static Sender produceMail() {
        return new MailSender();
    }
}




































