package EnumStudy;

public class Animal implements Comparable<Animal> {

    protected int age;

    public Animal(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Animal o) {
        return this.age - o.age;
    }
}



























