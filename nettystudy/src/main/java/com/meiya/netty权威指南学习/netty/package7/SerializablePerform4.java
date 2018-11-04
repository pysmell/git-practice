package com.meiya.netty权威指南学习.netty.package7;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.template.Templates;
import org.msgpack.unpacker.Unpacker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 序列化容器List、Map
 */
public class SerializablePerform4 {

    public static void main(String[] args) throws IOException {

        MessagePack messagePack = new MessagePack();

        Template<List<String>> listTemplate = Templates.tList(Templates.TString);

        Template<Map<String, String>> mapTemplate = Templates.tMap(Templates.TString, Templates.TString);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Packer packer = messagePack.createPacker(outputStream);

        List<String> stringList = new ArrayList<>();

        stringList.add("lqw");

        stringList.add("hll");

        stringList.add("lbf");

        packer.write(stringList);

        Map<String, String> map = new HashMap<>();

        map.put("1", "lqw");

        map.put("2", "hll");

        map.put("3", "lbf");

        packer.write(map);

        byte[] bytes = outputStream.toByteArray();

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        Unpacker unpacker = messagePack.createUnpacker(byteArrayInputStream);

        List<String> list = unpacker.read(listTemplate);

        System.out.println(Arrays.toString(list.toArray()));

        Map<String, String> stringMap = unpacker.read(mapTemplate);

        System.out.println(Arrays.toString(stringMap.keySet().toArray()));
    }


}
