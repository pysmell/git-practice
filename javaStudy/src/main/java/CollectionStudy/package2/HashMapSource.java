package CollectionStudy.package2;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/*
* java7 HashMap源码分析
* HashMap里面是一个数组，然后数组中每个元素是一个单向链表、单向链表中的每个实体是嵌套类Entry
* 的实例，Entry包含四个属性：key、value、hash值和用于单向链表的next
* capacity：当前数组容量，始终保持2^n，可以扩容，扩容后数组大小为当前的2倍
* loadFactory：负载因子，默认为0.75
* threshold：扩容的阈值，等于capacity*loadFactor
* 多线程操作的时候会出现死循环，发生在resize()方法中
* 假设有三个元素 [key:3,value:a],[key:7,value:a],[key:5,value:a]
* 刚刚开始都同属于一个数组元素的链表中，由于已经达到扩容的情况，需要将其复制到两倍的数组中
* 需要重新计算hash值，即hashcode的低几位与长度做&运算，计算出在所属数组的位置，首先将头key等于3这个元素重新放入
* 当第一个线程取到3这个元素，并且下一个元素next是7，由于线程调度的原因，线程1挂起
* 现在由线程2执行，假设key等于5的元素在新数组2中，key7和3在新数组4中，即7的next就是3
* 由于线程调度的原因，线程1开始执行，由于当前在线程1的工作内存中还记住key等于3这个节点，他的下一个节点是7，真正的情况是2执行完后，
* key等于3的这个next是null，7的下一个节点才是3，开始重新执行key：3放在数组4的头节点上，现在key：7开始执行
* key：7放在头节点，key：7的next是key：3，现在开始执行key：3，key：3放在头节点上，重新指向key：7，为此
* 会导致回环，因此当get()的时候会导致死循环
* */
public class HashMapSource<K,V> extends AbstractMap<K,V>
        implements Map<K,V>, Cloneable, Serializable {

    private static final long serialVersionUID = 362498820763181265L;

    //hashmap的最大大小
    static final int MAXIMUM_CAPACITY = 1 << 30;

    //负载因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    //计算hash值
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    transient Node<K,V>[] table;

    transient Set<Map.Entry<K,V>> entrySet;


    transient int size;

    transient int modCount;

    int threshold;


    //final float loadFactor;

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    //构造函数

}
