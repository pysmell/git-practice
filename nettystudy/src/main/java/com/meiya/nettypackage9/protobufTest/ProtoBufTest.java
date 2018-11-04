package com.meiya.nettypackage9.protobufTest;

/*
通常在TCP Socket通讯（RPC调用）相关的应用中使用；它本身非常简单，易于开发，而且结合Netty框架可以非常便捷的实现一个RPC应用程序，
同时Netty也为Protobuf解决了有关Socket通讯中“半包、粘包”等问题（反序列化时，字节成帧

* protoBuf使用说明 protobuf是一个高性能、易扩展的序列化框架
* 下载
* 在windows中 下载地址: https://github.com/google/protobuf/releases/tag/v3.3.0  下protoc-win32.zip
* 将解压包下的bin目录配置到环境变量，目的是为了使用proto.exe将.proto文件生成java 类
* proto文件语法解析
*   “message”表示，声明一个“类”，即java中的class。message中可以内嵌message，就像java的内部类一样。
*   一个message有多个filed，“required string name = 1”则表示：name字段在序列化、反序列化时为第一个字段，string类型，
*   “required”表示这个字段的值是必选；可以看出每个filed都至少有着三个部分组成，其中filed的“位置index”全局唯一。
*   “optional”表示这个filed是可选的（允许为null）。“repeated”表示这个filed是一个集合（list）。也可以通过[default = ]为
*   一个“optional”的filed指定默认值
*   我们可以在一个.proto文件中声明多个“message”，不过大部分情况下我们把互相继承或者依赖的类写入一个.proto文件，将那些没有关联关系的类分别写入不同的文件，这样便于管理。
*   我们可以在.proto文件的头部声明一些额外的信息，比如“java_package”表示当“generate code”时将生成的java代码放入指定的package中。“java_outer_classname”表示生成的java类的名称。
*   .proto文件写好之后用下列语句生成java文件
*   protoc --proto_path=D:\  --java_out=D:\ D:\personMsg.proto
*
*   protoBuf入门介绍
*   以上述Person.proto文件为例
*   message Person {
        required string name = 1;
        required int32 id = 2;
        optional string email = 3;
    }
     声明了三个filed，每个filed都“规则”、“类型”、“字段名称”和一个“唯一的数字tag”。
    1）其中“规则”可以为如下几个值：
    “required”：表示此字段值必填，一个结构良好的message至少有一个flied为“required”。
    “optional”：表示此字段值为可选的。对于此类型的字段，可以通过default来指定默认值，这是一个良好的设计习惯。
    optional int32 page = 3 [default = 10];
    如果没有指定默认值，在encoding时protobuf将会用一个特殊的默认值来替代。对于string，默认值为空，bool类型默认为false，数字类型默认位0，对于enum则默认值为枚举列表的第一个值
    “repeated”：表示这个字段的值可以允许被重复多次，如果转换成JAVA代码，此filed数据结构为list，有序的。可以在“repeated”类型的filed后使用“packed”--压缩，提高数据传输的效率。
    repeated int32 numbers = 4 [packed=true];
     特别需要注意：当你指定一个filed位required时，需要慎重考虑这个filed是否永远都是“必须的”。
     将一个required调整为optional，需要同时重新部署数据通讯的Client和Server端，否则将会对解析带来问题。
    2）可以在一个.proto文件中，同时声明多个message，这样是允许的。
    3）为message或者filed添加注释，风格和JAVA一样：
        optional int32 page = 3;// Which page number do we want?

    4）数据类型与JAVA对应关系：
        protobuf	java
        double	double
        float	float
        int32	int
        int64	long
        bool	boolean
        string	String
        bytes	ByteString
    其中“ByteString”是Protobuf自定义的JAVA API。
     5）枚举：和JAVA中Enum API一致，如果开发者希望某个filed的值只能在一些限定的列表中，可以将次filed声明为enum类型。
     Protobuf中，enum类型的每个值是一个int32的数字，不像JAVA中那样enum可以定义的非常复杂。如果enum中有些值是相同的，
     可以将“allow_alias”设定为true。
message Person {
  required Type type = 1;
  enum Type {
  option allow_alias = true;
    TEACHER = 0;
    STUDENT = 1;
    OTHER = 1;//the same as STUDENT
  }
}
    6）import：如果当前.proto文件中引用了其他proto文件的message类型，那么可以在此文件的开头声明import。
import "other_protos.proto";
    7）嵌入message：类似于java的内部类，即在message中，嵌入其他message。如Person.proto例子中的PhoneNumber。
    8）更新message类型：如果一个现有的message类型无法满足当前的需要，比如你需要新增一个filed，但是仍然希望使用生成的旧代码来解析。
        （1）不要修改现有fileds的数字tag，即字段的index数字。
        （2）新增字段必须为optional或者repeated类型，同时还要为它们设置“default”值，这意味着“old”代码序列化的
            messages能够被“new”代码解析。“new”代码生成的数据也能被“old”代码解析，对于“old”代码而言，那些没有被声明的filed将会在解析式忽略。
        （3）非“required”filed可以被删除，但是它的“数字tag”不能被其他字段重用。
        （4）int32、uint32、int64、uint64、bool，是互相兼容的，它们可以从一个类型修改成另外一个，而不会对程序带来错误。参见源码WireFormat.FiledType
        （5）sint32和sint64是兼容的，但和其他数字类型是不兼容的。
        （6）string和bytes是兼容的，只要为UTF-8编码的。注意protobuf中string默认是UTF-8编码的。
        （7）optional与repeated是兼容的。如果输入的数据格式是repeated，但是client希望接受的数据是optional，对于原生类型，那么client将会使用repeated的最后一个值，对于message类型，client将会merge这些输入的数据。
        （8）修改“default”值通常不会有任何问题，只要保证这个默认值不会被真正的使用。
        （9）map结构
其中key_type可以为任何“整形”或者string类型，value_type可以为任意类型，只要JAVA API能够支持。
map类型不能被“repeated”、“optional”或者“required”修饰，传输过程中无法确保map中数据的顺序，
对于文本格式，map是按照key排序。
        10）如下为一些有用的选项：
        （1）java_package：在.proto文件的顶部设定，指定生成JAVA文件时类所在的package。
        option java_package = "com.example.foo";
        （2）java_outer_classname：在.proto文件的顶部设定，指定生成JAVA文件时类的名字。
        一个.proto文件只会生成一个JAVA类。
        option java_outer_classname = "FooProtos";
        （3）packed：对于repeated类型有效，指定输入的数据是否“压缩”。

   5、protobuf序列化原理
      [serializedSize]{[int32(tag,type)][value]...}
      对于一个message，序列化时首先就算这个message所有filed序列化需要占用的字节长度，计算这个长度是非常简单的，
      因为protobuf中每种类型的filed所占用的字节数是已知的(bytes、string除外)，只需要累加即可。这个长度就是serializedSize，
      32位integer，在protobuf的某些序列化方式中可能使用varint32（一个压缩的、根据数字区间，使用不同字节长度的int）；
      此后是filed列表输出，每个filed输出包含int32(tag，type)和value的字节数组，从上文我们知道每个filed都有一个唯一的数字tag表示它
      的index位置，type为字段的类型，tag和type分别占用一个int的高位、低位字节；如果filed为string、bytes类型，还会在value之前额外的
      补充添加一个varint32类型的数字，表示string、bytes的字节长度。
      那么在反序列化的时候，首先读取一个32位的int表示serializedSize，然后读取serializedSize个字节保存在一个bytebuffer中，
      即读取一个完整的package。然后读取一个int32数字，从这个数字中解析出tag和type，如果type为string、bytes，然后补充读取一个varint32就知道了string的字节长度了，此后根据type或者字节长度，读取后续的字节数组并转换成java type。重复上述操作，直到整个package解析完毕。


      varint32的理解是一种紧凑的表示数字的方法。它用一个或多个字节来表示一个数字，值越小的数字使用越少的字节数。这能减少用来表示数字的字节数。 Varint 中的每个 byte 的最高位 bit 有特殊的含义，如果该位为 1，表示后续的 byte 也是该数字的一部分，如果该位为 0，则结束。其他的 7 个 bit 都用来表示数字。因此小于 128 的数字都可以用一个 byte 表示。大于 128 的数字，会用两个字节。
      最大的区别是消息头它不是固定长度（常见是的使用INT 4个字节固定长度），Varint它用一个或多个字节来表示一个数字决定它不是固定长度！

* */
public class ProtoBufTest {



}



















































































