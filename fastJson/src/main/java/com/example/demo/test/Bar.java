package com.example.demo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.Date;
import java.util.Random;

import static com.alibaba.fastjson.serializer.SerializerFeature.QuoteFieldNames;
import static com.alibaba.fastjson.serializer.SerializerFeature.UseSingleQuotes;

public class Bar {
    public static SerializeConfig mapping = new SerializeConfig();
    private String barName;
    private int barAge;
    private Date barDate = new Date();
    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
    }
    {
        Random r = new Random();
        barName = "sss_"+String.valueOf(r.nextFloat());
        barAge = r.nextInt();
    }
    public static void main(String[] args) {

        Object obj = JSON.toJSON(new Bar());
        System.out.println(obj);
        String x1 = JSON.toJSONString(new Bar(), true);
        System.out.println(x1);
        String x2 = JSON.toJSONString(new Bar(), mapping);
        System.out.println(x2);
        String x3 = JSON.toJSONString(new Bar(), UseSingleQuotes);
        System.out.println(x3);
    }

    public static SerializeConfig getMapping() {
        return mapping;
    }

    public static void setMapping(SerializeConfig mapping) {
        Bar.mapping = mapping;
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public int getBarAge() {
        return barAge;
    }

    public void setBarAge(int barAge) {
        this.barAge = barAge;
    }

    public Date getBarDate() {
        return barDate;
    }

    public void setBarDate(Date barDate) {
        this.barDate = barDate;
    }
}
