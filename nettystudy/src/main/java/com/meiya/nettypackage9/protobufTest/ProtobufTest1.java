package com.meiya.nettypackage9.protobufTest;

import com.example.tutorial.AddressBookProtos;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.List;

/*
* Protocol Buffers使用
* 是谷歌的一项技术，用于将结构化的数据序列化、反序列化，经常用于网络传输
* 也有其他的开源的序列化/反序列化框架
* 比如Apache Avro，Apache Thrift，这两个框架和Protobuf相比，性能非常接近，而且设计原理如出一辙；
* 其中Avro在大数据存储（RPC数据交换，本地存储）时比较常用；
* Thrift的亮点在于内置了RPC机制，所以在开发一些RPC交互式应用时，Client和Server端的开发与部署都非常简单
* byte[] toByteArray();: 序列化消息并返回一个包含它的原始字节的字节数组。
* static Person parseFrom(byte[] data);: 从给定的字节数组解析一个消息。
* void writeTo(OutputStream output);: 序列化消息并将消息写入 OutputStream。
* static Person parseFrom(InputStream input);: 从一个 InputStream 读取并解析消息。
* */
public class ProtobufTest1 {

    public static void main(String[] args) throws InvalidProtocolBufferException {

        AddressBookProtos.AddressBook pb = AddressBookProtos.AddressBook.newBuilder().addPeople(AddressBookProtos.Person.newBuilder().setEmail("382394323@qq.com").setId(34).setName("lqw")).addPeople(AddressBookProtos.Person.newBuilder().setEmail("1376506647@qq.com").setId(35).setName("hll")).build();

        byte[] bs = pb.toByteArray();

        AddressBookProtos.AddressBook pb1 = AddressBookProtos.AddressBook.parseFrom(bs);

        List<AddressBookProtos.Person> list = pb1.getPeopleList();

        list.forEach(p->{
            System.out.println(p.getEmail() + ";" + p.getName() + ";");
        });
    }
}
/*
* protobuf序列化三种方式
//第一种方式
//序列化
byte[] data = person.toByteArray();//获取字节数组，适用于SOCKET或者保存在磁盘。
//反序列化
PersonProtos.Person result = PersonProtos.Person.parseFrom(data);
System.out.println(result.getEmail());


   这种方式，适用于很多场景，Protobuf会根据自己的encoding方式，将JAVA对象序列化成字节数组。同时Protobuf也可以从字节数组中重新decoding，得到Java新的实例。

//第二种序列化：粘包,将一个或者多个protobuf对象字节写入stream。
ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//生成一个由：[字节长度][字节数据]组成的package。特别适合RPC场景
person.writeDelimitedTo(byteArrayOutputStream);
//反序列化，从steam中读取一个或者多个protobuf字节对象
ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
result = PersonProtos.Person.parseDelimitedFrom(byteArrayInputStream);
System.out.println(result.getEmail());

第二种方式，是RPC调用中、Socket传输时适用，在序列化的字节数组之前，添加一个varint32的数字表示字节数组的长度；那么在反序列化时，可以通过先读取varint，然后再依次读取此长度的字节；这种方式有效的解决了socket传输时如何“拆包”“封包”的问题。在Netty中，适用了同样的技巧。


//第三种序列化,写入文件或者Socket
FileOutputStream fileOutputStream = new FileOutputStream(new File("/test.dt"));
person.writeTo(fileOutputStream);
fileOutputStream.close();

FileInputStream fileInputStream = new FileInputStream(new File("/test.dt"));
result = PersonProtos.Person.parseFrom(fileInputStream);
System.out.println(result);


    第三种方式，比较少用。但是比较通用，意思为将序列化的字节数组写入到OutputStream中，具体的拆包工作，交给了高层框架。
*
*
*
* */



























