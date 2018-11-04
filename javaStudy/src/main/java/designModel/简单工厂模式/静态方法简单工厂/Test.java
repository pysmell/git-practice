package designModel.简单工厂模式.静态方法简单工厂;

public class Test {

    public static void main(String[] args) {
        Sender sender = SendFactory.produceMail();
        sender.send();
    }

}
