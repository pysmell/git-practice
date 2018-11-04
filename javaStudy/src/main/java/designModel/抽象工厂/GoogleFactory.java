package designModel.抽象工厂;

public class GoogleFactory implements Provider {

    @Override
    public Mouseer produceMouse() {
        return new GoogleMouse();
    }

    @Override
    public KeyBoard produceKeyBoard() {
        return new GoogleKeyBoard();
    }
}
