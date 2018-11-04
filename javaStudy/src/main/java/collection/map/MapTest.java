package collection.map;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {

        Map<String, Map> stringMapMap = new HashMap<>();

        Map map = new HashMap();

        stringMapMap.put("newMap", map);

        ((Map)stringMapMap.get("newMap")).put("linqw", "123");

        for (Map.Entry entry : stringMapMap.entrySet()) {
            Map valueMap = (Map) entry.getValue();
            for (Object entry1 : valueMap.entrySet()) {
                Map.Entry entry2 = (Map.Entry) entry1;
                System.out.println(entry2.getValue());
            }
        }




    }

}
