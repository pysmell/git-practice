package designModel.责任链模式;

public class Test {

    public static void main(String[] args) {

        MyHandler myHandler = new MyHandler("h1");

        MyHandler myHandler2 = new MyHandler("h2");

        MyHandler myHandler3 = new MyHandler("h3");

        myHandler.setHandler(myHandler2);

        myHandler2.setHandler(myHandler3);

        myHandler.operator();
    }

}
