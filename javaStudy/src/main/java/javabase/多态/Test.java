package javabase.多态;

public class Test {

    public static void main(String[] args) {

        TestParent testParent = new TestChild();
        TestParent testParent1 = new TestChild1();
        testParent.sayHello();
        testParent1.sayHello();
    }

}
