package CollectionStudy.package1;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;
public class ArrayListSource<E> extends AbstractList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable{

    /**
     * 默认数组大小
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 空数组，传入大小时，大小等于0时实例化用，一个元素，一个元素增加
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * 空数组，没有传入大小参数，实例化用此数组，动态的增加
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * 数组大小
     */
    private int size;

    /**
     * 传入容量构造函数
     */
    public ArrayListSource(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }

    /**
     * 无参构造函数
     */
    public ArrayListSource() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public ArrayListSource(Collection<? extends E> collection) {

        this.elementData = collection.toArray();

        if ((size = elementData.length) != 0) {
            if (elementData.getClass() != Object[].class) {
                elementData = Arrays.copyOf(elementData, size, Object[].class);
            }
        } else {
            elementData = EMPTY_ELEMENTDATA;
        }
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    } 

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >=0 ; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >=0 ; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 克隆，浅复制，即数组里的内容指向同一些元素，对这些元素修改其他也会修改，但是list中的某个元素删除，并不会删除其他list集合中的元素
     */
    public Object clone() {
        try {
            //?表示不确定的java类型,默认是允许Object及其下的子类，也就是java的所有对象了
            ArrayListSource<?> list = (ArrayListSource<?>) super.clone();
            list.elementData = Arrays.copyOf(elementData, size);
            return list;
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    public <T> T[] toArray(T[] a) {

        if (a.length < size)
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());

        System.arraycopy(elementData, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    E elementData(int index)
    {
        return (E) elementData[index];
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    public boolean add(E element) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = element;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        ensureExplicitCapacity(minCapacity);
    }

    private void ensureExplicitCapacity(int minCapacity) {

        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {

        int oldCapacity = elementData.length;

        int newCapacity = oldCapacity + oldCapacity >> 1;

        if (newCapacity < minCapacity) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            hugeCapacity(minCapacity);
        }
    }

    public static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return  minCapacity > MAX_ARRAY_SIZE?Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index <0) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    public void add(int index, E element) {
        ensureCapacityInternal(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    public E remove(int index) {

        rangeCheck(index);
        modCount++;
        int numMover = size - index - 1;
        E oldElement = elementData(index);
        System.arraycopy(elementData, index+1, elementData, index, numMover);
        elementData[size--] = null;
        return oldElement;
    }

    public boolean remove(Object o) {

        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    fastRemove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    fastRemove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public void fastRemove(int index) {
        int numMover = size - index - 1;
        System.arraycopy(elementData, index+1, elementData, index, numMover);
        elementData[size--] = null;
    }
 }
