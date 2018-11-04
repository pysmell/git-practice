package designModel.抽象工厂;

public class GoogleKeyBoard implements KeyBoard {
    @Override
    public void makeKeyBoard() {
        System.out.println("GoogleKeyBoard makeKeyBoard");
    }
}
