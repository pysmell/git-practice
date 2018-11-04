package javabase;

public class Parent {

    public static Print obj1 = new Print("1");

    public Print obj2 = new Print("2");

    public static Print obj3 = new Print("3");

    static {
        new Print("4");
    }
    {
        new Print("9");
    }
    public static Print obj4 = new Print("5");

    public Print obj5 = new Print("6");

    public Parent() {
        new Print("7");
    }
}
