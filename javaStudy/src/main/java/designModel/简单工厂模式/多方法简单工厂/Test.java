package designModel.简单工厂模式.多方法简单工厂;

public class Test {

    public static void main(String[] args) {
        SendFactory sendFactory = new SendFactory();
        Sender sender = sendFactory.produceMail();
        sender.send();
    }

}
