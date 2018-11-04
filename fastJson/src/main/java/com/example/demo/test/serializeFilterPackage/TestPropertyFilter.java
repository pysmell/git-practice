package com.example.demo.test.serializeFilterPackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * PropertyFilter,过滤不需要的属性,bean实体类，属性需要有get方法
 */
public class TestPropertyFilter {

    public static void main(String[] args) {
        PropertyFilter filter = new PropertyFilter() {

            @Override
            public boolean apply(Object source, String name, Object value) {

                System.out.println("----------------source=" + source);
                System.out.println("----------------name=" + name);
                System.out.println("----------------value=" + value);
                // 属性是id并且大于等于100时进行序列化
                if ("id".equalsIgnoreCase(name)) {
                    long id = ((Long) value).longValue();
                    return id >= 100;
                }
                return false;
            }
        };

        User user = new User();
        user.setId(9L);
        user.setName("linqw");

        String jsonString = JSON.toJSONString(user, filter); // 序列化的时候传入filter
        System.out.println("序列化,id=9：" + jsonString + "\n");

        user.setId(200L);
        jsonString = JSON.toJSONString(user, filter); // 序列化的时候传入filter
        System.out.println("序列化,id=200：" + jsonString);
    }

}
/**
 * PropertyPreFilter根据PropertyName判断是否序列化
 * PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化
 * NameFilter修改key，如果需要修改key，process返回值则可
 * ValueFilter 修改value
 * BeforeFilter序列化时在最前添加内容
 * AfterFilter序列化时在最后添加内容
 * public interface PropertyFilter extends SerializeFilter {
 *       boolean apply(Object object, String propertyName, Object propertyValue);
 * }
 */























































