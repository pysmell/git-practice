package javabase;

public class Child extends Parent{

    static{
        new Print("a");
    }

    public static Print obj1 = new Print("b");

    public Print obj2 = new Print("c");

    public Child (){
        new Print("d");
    }

    {
        new Print("g");
    }

    public static Print obj3 = new Print("e");

    public Print obj4 = new Print("f");

    public static void main(String [] args){
        Parent obj1 = new Child ();
        Parent obj2 = new Child ();
    }
}

/*
如果类还没有被加载：
1、先执行父类的静态代码块和静态变量初始化，并且静态代码块和静态变量的执行顺序只跟代码中出现的顺序有关。
2、执行子类的静态代码块和静态变量初始化。
3、执行父类的实例变量初始化
4、执行父类的构造函数
5、执行子类的实例变量初始化
6、执行子类的构造函数

如果类已经被加载：
则静态代码块和静态变量就不用重复执行，再创建类对象时，只执行与实例相关的变量初始化和构造方法。

* */