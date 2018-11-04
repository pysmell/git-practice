package CollectionStudy.package1;

import java.util.*;

/*
* 双向链表
* 有序，并且可重复，查询慢一个节点，一个节点遍历，增加快，删除快
* */
public class LinkedListSource<E> extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable, java.io.Serializable{

    transient int size = 0;

    transient Node<E> first;

    transient Node<E> last;

    public LinkedListSource() {
    }

    public boolean addAll(Collection<? extends E> c) {
       return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {

        checkPositionIndex(index);

        Object[] objects = c.toArray();

        int numNew = objects.length;
        if (numNew == 0) {
            return false;
        }

        Node<E> prev, succ;

        if (index == size) {
            succ = null;
            prev = last;
        } else {
            succ = node(index);
            prev = succ.prev;
        }

        for (Object o : objects) {
            E e = (E)o;
            Node<E> newNode = new Node<>(prev, e, null);

            //第一个元素
            if (prev == null) {
                first = newNode;
            } else {
                prev.next = newNode;
            }
            prev = newNode;
        }

        if (succ == null) {
            last = prev;
        } else {
            prev.next = succ;
            succ.prev = prev;
        }

        size += numNew;
        return true;
    }

    Node<E> node(int index) {
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = last.prev;
            }
            return x;
        }
    }


    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public void addFirst(E e) {

    }

    @Override
    public void addLast(E e) {

    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
