package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import static com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames;

public class SimpleTest {
    public static void main(String[] args) {
        JSONObject json = new JSONObject();
        json.put("aa","11");
        json.put("bb","22");
        json.put("cc","33");
        String jsonStr = json.toString();
        System.out.println(jsonStr);
        System.out.println(JSONObject.parseObject(jsonStr).get("aa") );
    }
}
