package com.meiya.netty权威指南学习.netty.package7;

import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.unpacker.Unpacker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * 序列化/反序列化基本类型，基本类型的包装类，String对象，byte[]对象和ByteBuffer对象
 */
public class SerializablePerform3 {

    public static void main(String[] args) throws IOException {

        MessagePack messagePack = new MessagePack();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Packer packer = messagePack.createPacker(byteArrayOutputStream);

        //序列化基本类型
        packer.write(true);
        packer.write(10);
        packer.write(10.1);
        //序列化基本类型的包装类
        packer.write(Boolean.TRUE);
        packer.write(new Double(10.1));
        //序列化多种类型的数组
        packer.write(new int[]{1, 2, 3, 4});
        packer.write(new Double[]{10.1, 250.25});
        packer.write(new String[]{"slj", "cz", "sg"});
        packer.write(new byte[]{1, 3, 1});
        //序列化其他引用类型
        packer.write("linqw");
        packer.write(ByteBuffer.wrap(new byte[]{1, 3, 1}));
        packer.write(BigInteger.ONE);
        byte[] bs = byteArrayOutputStream.toByteArray();

        //反序列化
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bs);
        Unpacker unpacker = messagePack.createUnpacker(byteArrayInputStream);
        boolean b = unpacker.readBoolean();
        System.out.println(b);
        int c = unpacker.readInt();
        System.out.println(c);
        double f = unpacker.readDouble();
        System.out.println(f);
        Boolean bool = unpacker.read(Boolean.class);
        System.out.println(bool);
        Double d = unpacker.read(Double.class);
        System.out.println(d);
        int[] j = unpacker.read(int[].class);
        System.out.println(Arrays.toString(j));
        Double[] doubles = unpacker.read(Double[].class);
        System.out.println(Arrays.toString(doubles));
        String[] strings = unpacker.read(String[].class);
        System.out.println(Arrays.toString(strings));
        byte[] bytes = unpacker.read(byte[].class);
        System.out.println(Arrays.toString(bytes));
        String text = unpacker.read(String.class);
        System.out.println(text);
        ByteBuffer byteBuffer = unpacker.read(ByteBuffer.class);
        byte[] newByte = new byte[byteBuffer.remaining()];
        byteBuffer.get(newByte);
        System.out.println(Arrays.toString(newByte));
        BigInteger bigInteger = unpacker.read(BigInteger.class);
        System.out.println(bigInteger);
    }

}
