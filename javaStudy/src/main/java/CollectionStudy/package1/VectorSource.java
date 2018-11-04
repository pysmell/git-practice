package CollectionStudy.package1;

import java.util.*;

public class VectorSource<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{

    protected Object[] elementData;

    protected int elementCount;

    protected int capacityIncrement;

    public VectorSource() {
        this(10);
    }
    public VectorSource(int initialCapacity) {
        this(initialCapacity, 0);
    }

    public VectorSource(int initialCapacity, int capacityIncrement) {
        super();
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }


    public VectorSource(Collection<? extends E> c) {
        elementData = c.toArray();
        elementCount = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, elementCount, Object[].class);
    }

    public synchronized void copyInto(Object[] objects) {
        System.arraycopy(elementData, 0, objects, 0, elementCount);
    }

    public synchronized void trimSize() {
        int oldCapacity = elementData.length;
        if (oldCapacity > elementCount) {
            elementData = Arrays.copyOf(elementData, elementCount);
        }
    }

    public synchronized void ensureCapacity(int minCapacity) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
