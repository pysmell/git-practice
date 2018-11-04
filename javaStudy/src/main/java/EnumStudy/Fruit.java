package EnumStudy;

/*
包含抽象方法的枚举类型,枚举类型经过编译器的处理，含抽象方法的将被处理成抽象类，否则处理成final类。

对于上面的枚举成员的形式也很容易理解，因为枚举成员是一个枚举类型的实例，上面的这种形式就是一种匿名内部类的形式，即每个枚举成员的创建可以理解成：

    BANANA = new Frutit("BANANA", 1) {//此构造器是编译器生成的，下面会说
        public void printFruitInfo() {//匿名内部类的抽象方法实现。
            System.out.println("This is apple");
        }
    };
事实上，编译器确实就是这样处理的，即上面的例子中，创建了三个匿名内部类，同时也会多创建三个class文件.
最后，我们反编译一下fruit类，看fruit类的定义：
* */
public enum Fruit {

    Apple {
        public void printFruitInfo() {
            System.out.println("Apple");
        }
    }, BANANA {
        public void printFruitInfo() {
            System.out.println("BANANA");
        }
    }, WATERMELON {
        public void printFruitInfo() {
            System.out.println("WATERMELON");
        }
    };

    public abstract void printFruitInfo();

    public static void main(String[] args) {
        Fruit.Apple.printFruitInfo();
    }

}
