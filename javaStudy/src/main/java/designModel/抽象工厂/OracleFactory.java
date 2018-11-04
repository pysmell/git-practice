package designModel.抽象工厂;

public class OracleFactory implements Provider {
    @Override
    public Mouseer produceMouse() {
        return new OracleMouse();
    }

    @Override
    public KeyBoard produceKeyBoard() {
        return new OracleKeyBoard();
    }
}
