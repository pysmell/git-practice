package designModel.迭代子模式;

public interface Collection<T> {

    Iterator<T> iterator();

    T get(int i);

    Integer size();

    Collection<T> add(T t);

}
