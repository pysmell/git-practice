package com.example.demo.test.serializeFilterPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;

public class TestValueFilter {

    public static void main(String[] args) {
        User user = new User();
        user.setId(9L);
        user.setName("linqw");
        String jsonString = JSON.toJSONString(user); // 序列化的时候传入filter
        System.out.println("普通序列化：" + jsonString + "\n");

        ValueFilter filter = new ValueFilter() {

            @Override
            public Object process(Object object, String name, Object value) {
                System.out.println("----------------object=" + object);
                System.out.println("----------------name=" + name);
                System.out.println("----------------value=" + value);
                System.out.println("");
                // 属性是id时修改id的值
                if ("id".equals(name)) {
                    long id = ((Long) value).longValue();
                    return id + "$";
                }
                return value;
            }
        };

        jsonString = JSON.toJSONString(user, filter); // 序列化的时候传入filter
        System.out.println("ValueFilter序列化：" + jsonString + "\n");
    }

}
/**
 * ValueFilter接口
 *  public interface ValueFilter extends SerializeFilter {
 *       Object process(Object object, String propertyName, Object propertyValue);
 *   }
 */