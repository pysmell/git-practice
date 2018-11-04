package CollectionStudy.package2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
* 存入的顺序和取出的顺序不一致，key不可重复，后面的value覆盖前面的value，HashMap允许存在一个为null的key，多个为null的value
* 具有很快的访问速度，遍历时，取得数据的顺序是完全随机
* */
public class HashMapTest {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("sk", "lqw");
        map.put("hdh", "hll");
        map.put("sdd", "lnc");
        map.put(null, null);
        map.put(null, "123");
        Set<String> stringSet = map.keySet();
        Iterator iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(map.get(iterator.next()));
        }

        /**
         * 效率比其他的遍历高
         * */
        Set<Map.Entry<String, String>> set =  map.entrySet();
        for (Map.Entry entry : set) {
            System.out.println("key: " + entry.getKey() + " " + "value:" + entry.getValue());
        }

    }
}
