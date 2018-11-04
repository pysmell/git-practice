package EnumStudy;

/*
* E extends Handphone<E> 类型E必须实现Handphone接口(要直接继承，不能通过父类间接继承)，并且这个接口的类型E(E本身或者其他类实现Handphone)，这样的话如果实现了comparable接口，就可以使是同一种Handphone类子类，并且是同一种类型
* <T extends Comparable<? super T>>
* 类型 T 必须实现 Comparable 接口，并且这个接口的类型是 T 或 T 的任一父类（T本身或者T的父类实现Comparable接口）。这样声明后，
* T 的实例之间，T 的实例和它的父类的实例之间，可以相互比较大小。例如，在实际调用时若使用的具体类是 Dog
* (假设 Dog 有一个父类 Animal），Dog 可以从 Animal 那里继承 Comparable<Animal> ，或者自己 implements Comparable<Dog> 。
* */
public abstract class Handphone<E extends Handphone<E>> implements Comparable<E> {

    abstract int osVersion();

    @Override
    public int compareTo(E o) {
        return osVersion() - o.osVersion();
    }
}
