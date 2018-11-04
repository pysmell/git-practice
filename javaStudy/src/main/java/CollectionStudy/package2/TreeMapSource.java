package CollectionStudy.package2;

import java.util.*;

/**
 * TreeMap源码分析
 * 非线程安全的不可重复元素的可排序键值对
 * 继承AbstractMap，实现了Cloneable，实现浅克隆
 * 实现了序列化接口，并自定义了readObject、writeObject方法
 * 内部采用红黑树，
 * 红黑树性质：
 * 性质 1：每个节点要么是红色，要么是黑色。
 * 性质 2：根节点永远是黑色的。
 * 性质 3：所有的叶节点都是空节点（即 null），并且是黑色的。
 * 性质 4：每个红色节点的两个子节点都是黑色。（从每个叶子到根的路径上不会有两个连续的红色节点）
 * 性质 5：从任一节点到其子树中每个叶子节点的路径都包含相同数量的黑色节点。
 * NavigableMap接口扩展的SortedMap，具有了针对给定搜索目标返回最接近匹配项的导航方法。
 * 方法lowerEntry、floorEntry、ceilingEntry和higherEntry分别返回与小于、小于等于、大于等于、
 * 大于给定键的键关联的Map.Entry对象，如果不存在这样的键，则返回null。类似地，方法lowerKey、
 * floorKey、ceilingKey和higherKey只返回关联的键。所有这些方法是为查找条目而不是遍历条目而设计的。
 */
public class TreeMapSource<K,V>
        extends AbstractMap<K,V>
        implements NavigableMap<K,V>, Cloneable, java.io.Serializable {

    //此比较器被用来维持tree map中元素的顺序，如果为null，则用key的自然顺序来进行排序
    private final Comparator<? super K> comparator;
    //根节点
    private transient Entry<K,V> root;

    //树中的节点数量
    private transient int size = 0;

    /**
     * The number of structural modifications to the tree.
     */
    private transient int modCount = 0;

    //构造一个新的，空的TreeMap对象，应用key的自然顺序对其排序，
    public TreeMapSource() {
        comparator = null;
    }

    public TreeMapSource(Comparator<? super K> comparator) {
        this.comparator = comparator;
    }

    public TreeMapSource(Map<? extends K, ? extends V> m) {
        comparator = null;
        putAll(m);
    }

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    //TreeMap的内部实现结构：红黑树
    static final class Entry<K,V> implements Map.Entry<K,V> {
        K key;
        V value;
        Entry<K,V> left;
        Entry<K,V> right;
        Entry<K,V> parent;
        boolean color = BLACK;

        /**
         * Make a new cell with given key, value, and parent, and with
         * {@code null} child links, and BLACK color.
         */
        //根据key、value和parent来创建一个节点对象，颜色为BLACK.
        Entry(K key, V value, Entry<K,V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
        // 判断节点相等的方法（两个节点为同一类型且key值和value值都相等时两个节点相等）
        public boolean equals(Object o) {
            if (!(o instanceof Map.Entry))
                return false;
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;

            return valEquals(key,e.getKey()) && valEquals(value,e.getValue());
        }
        // 节点的哈希值计算方法
        public int hashCode() {
            int keyHash = (key==null ? 0 : key.hashCode());
            int valueHash = (value==null ? 0 : value.hashCode());
            return keyHash ^ valueHash;
        }

        public String toString() {
            return key + "=" + value;
        }
    }

    static final boolean valEquals(Object o1, Object o2) {
        return (o1==null ? o2==null : o1.equals(o2));
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        int mapSize = map.size();
        if (size==0 && mapSize!=0 && map instanceof SortedMap) {
            Comparator<?> c = ((SortedMap<?,?>)map).comparator();
            if (c == comparator || (c != null && c.equals(comparator))) {
                ++modCount;
                /*try {
                    buildFromSorted(mapSize, map.entrySet().iterator(),null, null);

                } catch (java.io.IOException cannotHappen) {
                } catch (ClassNotFoundException cannotHappen) {
                }*/
                return;
            }
        }
        super.putAll(map);
    }

    /**
     * 1、根据key先在红黑树中找出其即将要插入的位置，并插入到树中
     * 2、由于添加节点会破坏红黑树，因此在插入元素之后要进行修正（颜色转换和数旋转），保持红黑树的5点性质。
     * 插入后的修复在插入操作中，红黑树的性质 1 和性质 3 两个永远不会发生改变，因此无需考虑红黑树的这两个特性。
     */
    public V put(K key, V value) {
        Entry<K,V> t = root;
        //如果根节点为null，则
        if (t == null) {
            //类型检查，如果k1或k2如果为null，则会抛出NullPointerException异常
            compare(key, key); // type (and possibly null) check

            root = new Entry<>(key, value, null); //新建的节点就为头结点
            size = 1;
            modCount++;
            return null;
        }
        int cmp;
        Entry<K,V> parent;
        // split comparator and comparable paths
        Comparator<? super K> cpr = comparator;
        if (cpr != null) { //指定了比较器的情况
            // do while实现在root为根节点移动寻找传入键值对需要插入的位置
            do {
                parent = t;
                // 使用比较器比较插入键值对的key值与父节点t.key的大小
                cmp = cpr.compare(key, t.key);
                if (cmp < 0) //如果小于父节点t的值，由于红黑树是一个自平衡二叉搜索树，因此，继续递归到父节点左节点
                    t = t.left;
                else if (cmp > 0)//如果大于父节点t的值，由于红黑树是一个自平衡二叉搜索树，因此，继续递归到父节点右节点
                    t = t.right;
                else
                    return t.setValue(value);//相等，则用新值替换旧值即可
            } while (t != null);
        }
        else { //没有指定比较器的情况
            if (key == null)
                throw new NullPointerException();
            @SuppressWarnings("unchecked")
            Comparable<? super K> k = (Comparable<? super K>) key;
            // 比较插入键值对的key值与父节点t.key的大小，基本思想与上面的思想一致
            do {
                parent = t;
                cmp = k.compareTo(t.key);
                if (cmp < 0)
                    t = t.left;
                else if (cmp > 0)
                    t = t.right;
                else
                    return t.setValue(value);
            } while (t != null);
        }
        //到这里，就找到了要插入的节点的位置
        Entry<K,V> e = new Entry<>(key, value, parent);
        //根据比较值来把此节点设置为parent的左节点还是右节点
        if (cmp < 0)//如果小于parent节点，则放在左节点
            parent.left = e;
        else
            parent.right = e;
        fixAfterInsertion(e);//插入元素之后要修正，保持红黑树的5点性质
        size++;
        modCount++;
        return null;
    }

    //一些小工具方法

    /**
     * Compares two keys using the correct comparison method for this TreeMap.
     */
    //在TreeMap中使用正确的比较器比较两个key的大小
    @SuppressWarnings("unchecked")
    final int compare(Object k1, Object k2) {
        //如果k1或k2如果为null，则会抛出NullPointerException异常
        return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
                : comparator.compare((K)k1, (K)k2);
    }

    private static <K,V> boolean colorOf(Entry<K,V> p) {
        return (p == null ? BLACK : p.color);
    }

    private static <K,V> Entry<K,V> parentOf() {
        return parentOf();
    }

    private static <K,V> Entry<K,V> parentOf(Entry<K,V> p) {
        return (p == null ? null: p.parent);
    }

    private static <K,V> void setColor(Entry<K,V> p, boolean c) {
        if (p != null)
            p.color = c;
    }

    private static <K,V> Entry<K,V> leftOf(Entry<K,V> p) {
        return (p == null) ? null: p.left;
    }

    private static <K,V> Entry<K,V> rightOf(Entry<K,V> p) {
        return (p == null) ? null: p.right;
    }

    /** From CLR */
    private void rotateLeft(Entry<K,V> p) {
        if (p != null) {
            Entry<K,V> r = p.right;
            p.right = r.left;
            if (r.left != null)
                r.left.parent = p;
            r.parent = p.parent;
            if (p.parent == null)
                root = r;
            else if (p.parent.left == p)
                p.parent.left = r;
            else
                p.parent.right = r;
            r.left = p;
            p.parent = r;
        }
    }

    /** From CLR */
    private void rotateRight(Entry<K,V> p) {
        if (p != null) {
            Entry<K,V> l = p.left;
            p.left = l.right;
            if (l.right != null) l.right.parent = p;
            l.parent = p.parent;
            if (p.parent == null)
                root = l;
            else if (p.parent.right == p)
                p.parent.right = l;
            else p.parent.left = l;
            l.right = p;
            p.parent = l;
        }
    }


    private void fixAfterInsertion(Entry<K,V> x) {
        // 将插入节点的颜色设置为红色
        x.color = RED;
        // 循环条件是x不为空且不是根节点且父节点的颜色是红色（如果父节点不是红色，则没有连续的红色节点，不再调整）
        //情形一：如果x是root节点，则不需要调整了，只需要设置为黑色即可
        while (x != null && x != root && x.parent.color == RED) {
            /*
                以下所有情况都是x的父节点的颜色为红色，牢记！！！
            */
            //如果x的父节点是x的父节点的父节点的左节点
            if (parentOf(x) == leftOf(parentOf(parentOf(x)))) {
                // 获取 x 的父节点的兄弟节点
                Entry<K,V> y = rightOf(parentOf(parentOf(x)));
                // 情形三：如果 x 的父节点的兄弟节点是红色，即父节点P和父节点的兄弟节点y都是红色
                if (colorOf(y) == RED) {
                    // 将 x 的父节点设为黑色
                    setColor(parentOf(x), BLACK);
                    // 将 x 的父节点的兄弟节点设为黑色
                    setColor(y, BLACK);
                    // 将 x 的父节点的父节点设为红色
                    setColor(parentOf(parentOf(x)), RED);
                    //递归地进行整个过程，把parentOf(parentOf(x))当成是新插入的节点进行处理即可
                    x = parentOf(parentOf(x));
                } else {
                    //情形四：如果 x 的父节点的兄弟节点是黑色或者缺少，且x为父节点的右节点
                    if (x == rightOf(parentOf(x))) {
                        //左旋转
                        x = parentOf(x);
                        rotateLeft(x);
                    }
                    //情形 5：父节点 P 是红色、而其兄弟节点 U 是黑色或缺少；且新节点 x 是其父节点的左子节点，而父节点 P 又是其父节点 PP的左子节点。
                    setColor(parentOf(x), BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    rotateRight(parentOf(parentOf(x)));
                }
            } else {//如果x的父节点是x的父节点的父节点的右节点

                // 获取 x 的父节点的兄弟节点
                Entry<K,V> y = leftOf(parentOf(parentOf(x)));
                //情形三：(同上) x的父节点的兄弟节点为红色，即x的父节点P和P的兄弟节点均为红色
                if (colorOf(y) == RED) {
                    setColor(parentOf(x), BLACK);
                    setColor(y, BLACK);
                    setColor(parentOf(parentOf(x)), RED);
                    x = parentOf(parentOf(x));
                } else {
                    //情形四：如果 x 的父节点的兄弟节点是黑色或者缺少，且x为父节点的左节点
                    if (x == leftOf(parentOf(x))) {
                        x = parentOf(x);
                        rotateRight(x);//右旋
                    }
                    // 把 x 的父节点设为黑色
                    setColor(parentOf(x), BLACK);
                    // 把 x 的父节点的父节点设为红色
                    setColor(parentOf(parentOf(x)), RED);
                    //左旋
                    rotateLeft(parentOf(parentOf(x)));
                }
            }
        }
        root.color = BLACK;
    }
    //函数功能：获取节点p的父节点。
   /* private static <K,V> Entry<K,V> parentOf(Entry<K,V> p) {
        return (p == null ? null: p.parent);
    }*/
    //函数功能：获取节点p的左节点
    /*private static <K,V> Entry<K,V> leftOf(Entry<K,V> p) {
        return (p == null) ? null: p.left;
    }*/
    //函数功能：获取节点p的右节点
    /*private static <K,V> Entry<K,V> rightOf(Entry<K,V> p) {
        return (p == null) ? null: p.right;
    }*/

    public static void main(String[] args) {

        TreeMap<String, Integer> stringIntegerMap = new TreeMap<>();
        stringIntegerMap.put("linqw", 25);
        stringIntegerMap.put("hll", 26);
        stringIntegerMap.put("lbf", 24);

        Map.Entry m = stringIntegerMap.lowerEntry("linqw");

        System.out.println(m.getKey());
    }


    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return null;
    }

    @Override
    public K lowerKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        return null;
    }

    @Override
    public K floorKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        return null;
    }

    @Override
    public K ceilingKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        return null;
    }

    @Override
    public K higherKey(K key) {
        return null;
    }

    @Override
    public Entry<K, V> firstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> lastEntry() {
        return null;
    }

    @Override
    public Entry<K, V> pollFirstEntry() {
        return null;
    }

    @Override
    public Entry<K, V> pollLastEntry() {
        return null;
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return null;
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return null;
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return null;
    }

    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super K> comparator() {
        return null;
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return null;
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return null;
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return null;
    }

    @Override
    public K firstKey() {
        return null;
    }

    @Override
    public K lastKey() {
        return null;
    }
}
/**
 * 红黑树在插入元素之后，要进行调整确实比较复杂，有如下5中情况：
 情形 1：
    新节点 N 是树的根节点，没有父节点
    在这种情形下，直接将它设置为黑色以满足性质 2。
 情形 2：
    新节点的父节点 P 是黑色在这种情况下，新插入的节点是红色的，因此依然满足性质 4。
    而且因为新节点 N 有两个黑色叶子节点；但是由于新节点 N 是红色，通过它的每个子节点
    的路径依然保持相同的黑色节点数，因此依然满足性质 5。
 情形 3：
    如果父节点 P 和父节点的兄弟节点 U 都是红色
    在这种情况下，程序应该将 P 节点、U 节点都设置为黑色，并将 P 节点的父节点设为红色
    （用来保持性质 5）。现在新节点 N 有了一个黑色的父节点 P。由于从 P 节点、U 节点
    到根节点的任何路径都必须通过 G 节点，在这些路径上的黑节点数目没有改变（原来有叶子和 G 节点两个黑色节点，现在有叶子和 P 两个黑色节点）。
    经过上面处理后，红色的 G 节点的父节点也有可能是红色的，这就违反了性质 4，因此还需要对 G 节点递归地进行整个过程（把 G 当成是新插入的节点进行处理即可）。
 情形 4：父节点 P 是红色、而其兄弟节点 U 是黑色或缺少；且新节点 N 是父节点 P 的右子节点，而父节点 P 又是其父节点 G 的左子节点。
    在这种情形下，我们进行一次左旋转对新节点和其父节点进行，接着按情形 5 处理以前的父
    节点 P（也就是把 P 当成新插入的节点即可）。这导致某些路径通过它们以前不通过的新节点 N 或父节点 P
    的其中之一，但是这两个节点都是红色的，因此不会影响性质 5。
    (01) 将“父节点”作为“新的当前节点”。
    (02) 以“新的当前节点”为支点进行左旋。
    在执行情景5
 情形 5：父节点 P 是红色、而其兄弟节点 U 是黑色或缺少；且新节点 N 是其父节点的左子节点，而父节点 P
 又是其父节点 G 的左子节点。在这种情形下，需要对节点 G 的一次右旋转，在旋转产生的树中，以前的父节点 P
 现在是新节点 N 和节点 G 的父节点。由于以前的节点 G 是黑色，否则父节点 P 就不可能是红色，我们切换以前
 的父节点 P 和节点 G 的颜色，使之满足性质 4，性质 5 也仍然保持满足，因为通过这三个节点中任何一个的所
 有路径以前都通过节点 G，现在它们都通过以前的父节点 P。在各自的情形下，这都是三个节点中唯一的黑色节点。
 (01) 将“父节点”设为“黑色”。
 (02) 将“祖父节点”设为“红色”。
 (03) 以“祖父节点”为支点进行右旋。
 */