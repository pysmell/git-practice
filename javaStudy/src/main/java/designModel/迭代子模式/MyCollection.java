package designModel.迭代子模式;

import java.util.ArrayList;
import java.util.List;

public class MyCollection<T> implements Collection<T> {

    private final List<T> tList = new ArrayList<>();

    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(this);
    }

    @Override
    public T get(int i) {
        return tList.get(i);
    }

    @Override
    public Integer size() {
        return tList.size();
    }

    @Override
    public Collection<T> add(T t) {
        tList.add(t);
        return this;
    }

}
