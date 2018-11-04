package collection.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapTest2 {

    public static void main(String[] args) {

        Long l = 1L;

        Map<String, Map> mapMap = new HashMap<>();

        for (int i=0;i<10;i++) {

            Map<String, Integer> map = new HashMap<>();

            map.put("key"+i, i);

            mapMap.put("map"+i, map);

        }

        Iterator iterator = mapMap.entrySet().iterator();
        int i = 0;
        while (iterator.hasNext()) {

            Map.Entry<String, Map> map = (Map.Entry<String, Map>) iterator.next();

            System.out.println(map.getKey());

            System.out.println(map.getValue());

            i++;
        }


    }


}
