package designModel.中介者模式;

public class Landlord extends Person {

    public Landlord(Mediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void sendMessage(String message) {
        mediator.operation(this, message);
    }

    @Override
    public void getMessage(String message) {

        System.out.println("[房东] " + name + "收到：" + message + "消息");

    }
}
