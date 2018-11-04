package com.example.demo.test.SerializerFeaturePackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.*;

/**
 * SerializerFeature
 */
public class SerializerFeatureClass {

    private static Word word;

    private static void init() {
        word = new Word();
        word.setA("a");
        word.setB(2);
        word.setC(true);
        word.setD("d");
        word.setE("");
        word.setF(null);
        word.setDate(new Date());
        List<User> list = new ArrayList<User>();
        User user1 = new User();
        user1.setId(1);
        user1.setOld("11");
        user1.setName("用户1");
        user1.setAdd("北京");
        User user2 = new User();
        user2.setId(2);
        user2.setOld("22");
        user2.setName("用户2");
        user2.setAdd("上海");
        User user3 = new User();
        user3.setId(3);
        user3.setOld("33");
        user3.setName("用户3");
        user3.setAdd("广州");

        list.add(user3);
        list.add(user2);
        list.add(null);
        list.add(user1);

        word.setList(list);

        Map<String, Object> map = new HashMap<>();
        map.put("mapa", "mapa");
        map.put("mapo", "mapo");
        map.put("mapz", "mapz");
        map.put("user1", user1);
        map.put("user3", user3);
        map.put("user4", null);
        map.put("list", list);
        word.setMap(map);
    }

    /**
     * 9、自定义
     * 格式化输出
     * 显示值为null的字段
     * 将为null的字段值显示为""
     * DisableCircularReferenceDetect:消除循环引用
     */
    private static void showJsonBySelf() {
        init();
        System.out.println(JSON.toJSONString(word, true));
        System.out.println(JSON.toJSONString(word, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty));
    }

    /**
     * 将bean转为array
     */
    private static void beanToArray() {
        init();
        /*word.setMap(null);
        word.setList(null);*/
        System.out.println(JSON.toJSONString(word, true));
        System.out.println(JSON.toJSONString(word, SerializerFeature.BeanToArray, SerializerFeature.PrettyFormat));
    }

    /**
     * 6:
     * PrettyFormat
     */
    private static void prettyFormat() {
        word.setMap(null);
        word.setList(null);
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.PrettyFormat));
    }

    /**
     * SortField:按字段名称排序后输出。默认为false
     * 这里使用的是fastjson：为了更好使用sort field martch优化算法提升parser的性能，fastjson序列化的时候，
     * 缺省把SerializerFeature.SortField特性打开了。
     * 反序列化的时候也缺省把SortFeidFastMatch的选项打开了。
     * 这样，如果你用fastjson序列化的文本，输出的结果是按照fieldName排序输出的，parser时也能利用这个顺序进行优化读取。
     * 这种情况下，parser能够获得非常好的性能。
     */
    private static void sortField() {
        System.out.println(JSON.toJSONString(word));
        System.out.println(JSON.toJSONString(word, SerializerFeature.SortField));
    }

    /**
     * 4:
     * WriteNullListAsEmpty:List字段如果为null,输出为[],而非null
     * 需要配合WriteMapNullValue使用，现将null输出
     */
    private static void writeNullListAsEmpty() {
        init();
        word.setList(null);
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置WriteNullListAsEmpty后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty));
    }


    /**
     * 3:
     * UseISO8601DateFormat:Date使用ISO8601格式输出，默认为false
     */
    private static void useISO8601DateFormat() {
        init();
        System.out.println(JSONObject.toJSONString(word, true));
        System.out.println("设置UseISO8601DateFormat后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseISO8601DateFormat, SerializerFeature.PrettyFormat));
    }

    /**
     * 2:
     * WriteMapNullValue:是否输出值为null的字段,默认为false
     */
    private static void writeMapNullValue() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置WriteMapNullValue后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 1:
     * UseSingleQuotes:使用单引号而不是双引号,默认为false
     */
    private static void useSingleQuotes() {
        System.out.println(JSONObject.toJSONString(word));
        System.out.println("设置useSingleQuotes后：");
        System.out.println(JSONObject.toJSONString(word, SerializerFeature.UseSingleQuotes));
    }

    public static void main(String[] args) {
        //showJsonBySelf();
        //beanToArray();
        //writeNullListAsEmpty();
        useISO8601DateFormat();
    }

}
/**
 * SerializerFeature属性
 * QuoteFieldNames 输出key时是否使用双引用，默认为true
 * UseSingleQuotes 使用单引号而不是双引号，默认为false
 * WriteMapNullValue 是否输出值为null的字段，默认为false
 * WriteEnumUsingToString Enum输出name()或者orgninal，默认为false
 * UseISO8601DateFor Date使用ISO8601格式输出，默认为false
 * WriteNullListAsEmpty List字段如果为null，输出[],而非null
 * WriteNullStringAsEmpty 字符类型字段如果为null，输出为"",而非null
 * WriteNullNumberAsZero 数值字段如果为null，输出为0，而非null
 * WriteNullBooleanAsFalse Boolean字段如果为null，输出为false，而非null
 * BeanToArray	将对象转为array输出
 * SkipTransientField	如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
 * SortField	按字段名称排序后输出。默认为false
 * WriteTabAsSpecial	把\t做转义输出，默认为false	不推荐
 * PrettyFormat	结果是否格式化,默认为false
 * WriteClassName	序列化时写入类型信息，默认为false。反序列化是需用到
 * DisableCircularReferenceDetect	消除对同一对象循环引用的问题，默认为false
 * WriteSlashAsSpecial	对斜杠’/’进行转义
 * BrowserCompatible	将中文都会序列化为格式，字节数会多一些，但是能兼容IE 6，默认为false
 * WriteDateUseDateFormat	全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
 * DisableCheckSpecialChar	一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
 */






























