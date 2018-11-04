package designModel.迭代子模式;

public class MyIterator<T> implements Iterator<T> {

    private Collection<T> collection;
    private int pos = -1;

    public MyIterator(Collection<T> collection) {
        this.collection = collection;
    }

    @Override
    public boolean previous() {

        return pos >= 1;
    }

    @Override
    public T next() {

        pos = pos + 1;

        return collection.get(pos);
    }

    @Override
    public boolean hasNext() {

        return pos < collection.size() - 1;

    }

    @Override
    public T first() {
        pos = 0;
        return collection.get(0);
    }
}
