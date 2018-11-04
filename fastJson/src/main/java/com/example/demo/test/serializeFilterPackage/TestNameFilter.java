package com.example.demo.test.serializeFilterPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PascalNameFilter;

/**
 * NameFilter,序列化时对key进行修改
 */
public class TestNameFilter {

    public static void main(String[] args) {

        User user = new User();
        user.setId(9L);
        user.setName("linqw");

        String jsonString = JSON.toJSONString(user);
        System.out.println("普通序列化：" + jsonString);

        NameFilter nameFilter = new NameFilter() {
            @Override
            public String process(Object o, String s, Object o1) {
                System.out.println("object=" + o);
                System.out.println("name=" + s);
                System.out.println("value=" + o1);
                if ("id".equals(s)) {
                    return s + "$";
                }
                return s;
            }
        };
        jsonString = JSON.toJSONString(user, nameFilter);
        System.out.println(jsonString);

        // fastjson内置一个PascalNameFilter，用于输出将首字符大写的Pascal风格
        jsonString = JSON.toJSONString(user, new PascalNameFilter()); // 序列化的时候传入filter
        System.out.println("PascalNameFilter序列化：" + jsonString + "\n");
    }

}
/**
 * NameFilter接口
 * public interface NameFilter extends SerializeFilter {
 *     String process(Object object, String propertyName, Object propertyValue);
 * }
 */









































