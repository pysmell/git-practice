package 泛型的理解;

import java.util.ArrayList;
import java.util.List;

public class CovariantArrays {

    public static void main(String[] args) {

        //上界,不允许增加元素
        List<? extends Fruit> fruits = new ArrayList<Apple>();

        //会报错
        //fruits.add(new Apple());
        //fruits.add(new Fruit());

        fruits.add(null);

        //下界,不允许增加父类元素，可以添加其子类和其本身
        List<? super Apple> flistBottem = new ArrayList<>();

        flistBottem.add(new Apple());
        flistBottem.add(new Jonathan());

        Object object = flistBottem.get(0);
    }

}

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}








































