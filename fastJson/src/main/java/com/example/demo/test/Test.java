package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.*;

public class Test {
    private static SerializeConfig mapping = new SerializeConfig();
    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }
    public static void main(String[] args) {
        Foo f1 = new Foo();
        Date date = new Date();
        String text = JSON.toJSONString(date, mapping);
        System.out.println(text);
        System.out.println(JSON.toJSONString(f1, true));
        String x2 =JSON.toJSONString(f1, mapping);
        System.out.println(x2);
    }

   /* public static void main(String[] args) {
        List<Bar> barList = new ArrayList<>();
        barList.add(new Bar());
        barList.add(new Bar());
        barList.add(new Bar());
        String json = JSON.toJSONString(barList, true);
        System.out.println(json);
        List<Bar> barList1 = JSON.parseArray(json, Bar.class);
        for (Bar bar : barList1) {
            System.out.println(bar.toString());
        }
    }*/

  /*  public static void main(String[] args) {
        Map<String,Bar> map = new HashMap<>();
        map.put("a", new Bar());
        map.put("b", new Bar());
        map.put("c", new Bar());
        String json = JSON.toJSONString(map,true);
        System.out.println(json);

        Map<String, Bar> map1 = (Map<String, Bar>) JSON.parse(json);
        for (String key : map1.keySet()) {
            System.out.println(key+":"+map1.get(key));
        }
    }*/
 /*   public static void main(String[] args) {
        String[] arr_String = {"a", "b", "c"};
        String json_arr_String = JSON.toJSONString(arr_String, true);
        System.out.println(json_arr_String);
        JSONArray jsonArray = JSON.parseArray(json_arr_String);
        for(Object o : jsonArray) {
            System.out.println(o);
        }
        System.out.println(jsonArray);


        Bar[] arr_Bar    = {new Bar(),new Bar(),new Bar()};
        String json_arr_Bar = JSON.toJSONString(arr_Bar,true);
        System.out.println(json_arr_Bar);
        JSONArray jsonArray2 = JSON.parseArray(json_arr_Bar);
        for (Object o : jsonArray2) {
            System.out.println(o);
        }
        System.out.println(jsonArray2);
    }*/

}
