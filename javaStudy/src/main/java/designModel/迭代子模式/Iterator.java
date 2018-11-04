package designModel.迭代子模式;

public interface Iterator<T> {

    boolean previous();

    T next();

    boolean hasNext();

    T first();
}
