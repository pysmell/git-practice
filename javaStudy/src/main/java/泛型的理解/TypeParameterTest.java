package 泛型的理解;

import EnumStudy.Animal;
import EnumStudy.Dog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TypeParameterTest {
    //T只是个类型参数
    public static <T extends Comparable<T>> void mysort1(List<T> list) {
        Collections.sort(list);
    }

    public static <T extends Comparable<? super T>> void mysort2(List<T> list) {
        Collections.sort(list);
    }

    public static void main(String[] args) {

        List<Animal> animalList = new ArrayList<>();
        animalList.add(new Animal(20));
        animalList.add(new Dog(10));

        //mysort1(animalList);  编译通过 因为自身继承了Comparable接口
        //System.out.println(animalList);
        mysort2(animalList); //编译通过
        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog(16));
        dogList.add(new Dog(18));
        //mysort1(dogList);编译不通过  因为sort1传入的T要求本身要直接继承Comparable接口
        mysort2(dogList);

    }
}
