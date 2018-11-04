package designModel.工厂方法;

public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("SmsSender send");
    }
}
