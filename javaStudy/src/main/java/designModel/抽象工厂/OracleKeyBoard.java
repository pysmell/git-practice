package designModel.抽象工厂;

public class OracleKeyBoard implements KeyBoard {
    @Override
    public void makeKeyBoard() {
        System.out.println("OracleKeyBoard makeKeyBoard");
    }
}
