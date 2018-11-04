package designModel.观察者模式;

public class Test {

    public static void main(String[] args) {

        Subject subject = new MySubject();

        subject.add(new Observer1());
        subject.add(new Observer2());
        subject.operation();
    }

}
