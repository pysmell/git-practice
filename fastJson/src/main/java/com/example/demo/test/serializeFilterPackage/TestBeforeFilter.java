package com.example.demo.test.serializeFilterPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.BeforeFilter;

/**
 * 在序列化对象的所有属性之前执行某些操作
 */
public class TestBeforeFilter {

    public static void main(String[] args) {

        User user = new User();
        user.setId(2L);
        user.setName("linqw");

        BeforeFilter beforeFilter = new BeforeFilter() {
            @Override
            public void writeBefore(Object o) {

                System.out.println(o);
                User user = (User)o;
                System.out.println("----------------User.id=" + user.getId() + " " + "User.name=" + user.getName() + "\n");
                user.setName(user.getName() + "$$$$");
            }
        };

        //普通序列化
        String jsonString = JSON.toJSONString(user);
        System.out.println(jsonString);

        jsonString = JSON.toJSONString(user, beforeFilter); // 序列化的时候传入filter
        System.out.println("BeforeFilter序列化：" + jsonString + "\n");
    }

}

/**
 * BeforeFilter 接口
 * public abstract class BeforeFilter implements SerializeFilter {
 *       protected final void writeKeyValue(String key, Object value) { ... }
 *       // 需要实现的抽象方法，在实现中调用writeKeyValue添加内容
 *       public abstract void writeBefore(Object object);
 *   }
 */