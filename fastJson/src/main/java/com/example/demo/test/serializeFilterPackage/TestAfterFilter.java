package com.example.demo.test.serializeFilterPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.AfterFilter;

/**
 * 自测AfterFilter
 */
public class TestAfterFilter {
    public static void main(String[] args) {
        User user = new User();
        user.setId(9L);
        user.setName("linqw");

        String jsonString = JSON.toJSONString(user); // 序列化的时候传入filter
        System.out.println("普通序列化：" + jsonString + "\n");

        AfterFilter filter = new AfterFilter() {

            @Override
            public void writeAfter(Object object) {

                User user = (User) object;
                System.out.println("------------User.id=" + user.getId() + " " + "User.name=" + user.getName() + "\n");
                user.setName(user.getName() + "$$$");
                writeKeyValue("key", "six");
            }
        };

        jsonString = JSON.toJSONString(user, filter); // 序列化的时候传入filter
        System.out.println("AfterFilter序列化：" + jsonString + "\n");
        System.out.println(user);
    }
}

/**
 *  public abstract class AfterFilter implements SerializeFilter {
 *       protected final void writeKeyValue(String key, Object value) { ... }
 *       // 需要实现的抽象方法，在实现中调用writeKeyValue添加内容
 *       public abstract void writeAfter(Object object);
 *   }
 */