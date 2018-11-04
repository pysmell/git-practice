package designModel.迭代子模式;

public class Test {

    public static void main(String[] args) {

        Collection<String> collection = new MyCollection<>();

        collection.add("a").add("b").add("c");

        Iterator<String> iterator = collection.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

}
