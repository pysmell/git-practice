package designModel.抽象工厂;

public class Test {

    public static void main(String[] args) {
        OracleFactory oracleFactory = new OracleFactory();
        Mouseer mouseer = oracleFactory.produceMouse();
        mouseer.makeMouse();
        KeyBoard keyBoard = oracleFactory.produceKeyBoard();
        keyBoard.makeKeyBoard();
    }

}
/**
 * 多个抽象产品类，每个抽象产品类可以派生出多个具体产品类。
 * 一个抽象工厂类，可以派生出多个具体工厂类。
 * 每个具体工厂类可以创建多个具体产品类的实例，也就是创建的是一个产品线下的多个产品
 */