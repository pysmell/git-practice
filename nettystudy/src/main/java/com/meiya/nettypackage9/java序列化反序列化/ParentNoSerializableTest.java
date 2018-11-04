package com.meiya.nettypackage9.java序列化反序列化;

import java.io.*;

public class ParentNoSerializableTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        serializableChild();

        Child child = deserializableChild();

        System.out.println(child);
    }

    public static void serializableChild() throws IOException {

        Child child = new Child(1000, "别墅", "二哈", "大哈", 25);

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("G:child.txt")));

        objectOutputStream.writeObject(child);
    }

    public static Child deserializableChild() throws IOException, ClassNotFoundException {

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("G:child.txt")));

        Child child = (Child) objectInputStream.readObject();

        return child;
    }
}
/*
* 如果几个类中的成员变量是同一个其它符合类型的Java类，而这个类有实现Serializable接口，序列化为同一个对象
*
*
* */


/*
* java序列化与反序列化系列问题
* 1）java中的Serializable接口和Externalizable接口区别
*    Externalizable接口提供了两个方法writeExternal()和readExternal()。这两个方法给我们提供了灵活处理Java序列化的方法，通过实现这个接口中的两个方法进行对象序列化可以替代Java中默认的序列化方法。
*    正确的实现Externalizable接口可以大幅度的提高应用程序的性能。
* 2）Serializable接口中几个方法？如果没有方法的话，那么这么设计Serializable接口的目的是什么？
*    Serializable接口在lang包中，是Java序列化机制的核心组成部分。里面没有包含任何方法，我们称这样的接口为标识接口。如果你的类实现了
*    Serializable接口，意味着这个类被打上了“可以进行序列化”的标签，并且也给了编译器指示，可以使用序列化对这个对象进行序列化
*3）什么是serialVersionUID？如果你没有定义serialVersionUID意味着什么？

SerialVersionUID应该是你的类中的一个public static final类型的常量，如果你的类中没有定义的话，那么编译器将抛出警告。如果你的类中没有制定serialVersionUID，
那么Java编译器会根据类的成员变量和一定的算法生成用来表达对象的
serialVersionUID ，通常是用来表示类的哈希值（hash code）。结论是，如果你的类没有实现SerialVersionUID，那么如果你的类中如果加入或者改变成员变量，
那么已经序列化的对象将无法反序列化。这是以为，类的成员变量的改变意味这编译器生成的SerialVersionUID的值不同。Java序列化过程是通过正确
SerialVersionUID来对已经序列化的对象进行状态恢复。

4）当对象进行序列化的时候，如果你不希望你的成员变量进行序列化，你怎么办？

这个问题也会这么问，如何使用暂态类型的成员变量？暂态和静态成员变量是否会被序列化等等。如果你不希望你的对象中的成员变量的状态得以保存，
你可以根据需求选择transient或者static类型的变量，这样的变量不参与Java序列化处理的过程。

5）如果一个类中的成员变量是其它符合类型的Java类，而这个类没有实现Serializable接口，那么当对象序列化的时候会怎样？

如果你的一个对象进行序列化，而这个对象中包含另外一个引用类型的成员编程，而这个引用的类没有实现Serializable接口，那么当对象进行序列化的时候会抛出“NotSerializableException“的运行时异常。

6）如果一个类是可序列化的，而他的超类没有，那么当进行反序列化的时候，那些从超类继承的实例变量的值是什么？

Java中的序列化处理实例变量只会在所有实现了Serializable接口的继承支路上展开。所以当一个类进行反序列化处理的时候，超类没有实现Serializable接口，
那么从超类继承的实例变量会通过为实现序列化接口的超类的构造函数进行初始化。

7)Can you Customize Serialization process or can you override defaultSerialization process in Java?

7）你能够自定义序列化处理的代码吗或者你能重载Java中默认的序列化方法吗？

答案是肯定的，可以。我们都知道可以通过ObjectOutputStream中的writeObject()方法写入序列化对象，通过ObjectInputStream中的readObject()
读入反序列化的对象。这些都是Java虚拟机提供给你的两个方法。如果你在你的类中定义了这两个方法，那么JVM就会用你的方法代替原有默认的序列化
机制的方法。你可以通过这样的方式类自定义序列化和反序列化的行为。需要注意的一点是，最好将这两个方法定义为private，以防止他们被继承、重写和重载。
也只有JVM可以访问到你的类中所有的私有方法，你不用担心方法私有不会被调用到，Java序列化过程会正常工作。

8）假设一个新的类的超类实现了Serializable接口，那么如何让这个新的子类不被序列化？

如果一个超类已经序列化了，那么无法通过是否实现什么接口的方式再避免序列化的过程了，但是也还有一种方式可以使用。那就是需要你在你的类中重新实现writeObject()和readObject()方法，并在方法实现中通过抛出NotSerializableException。

9）在Java进行序列化和反序列化处理的时候，哪些方法被使用了？

这个是面试中常见的问题，主要用来考察你是否对readObject()、writeObject()、readExternal()和writeExternal()方法的使用熟悉。Java序列化是通过java.io.ObjectOutputStream这个类来完成的。
这个类是一个过滤器流，这个类完成对底层字节流的包装来进行序列化处理。我们通过ObjectOutputStream.writeObject(obj)进行序列化，
通过ObjectInputStream.readObject()进行反序列化。对writeObject()方法的调用会触发Java中的序列化机制。
readObject()方法用来将已经持久化的字节数据反向创建Java对象，该方法返回Object类型，需要强制转换成你需要的正确类型。

10)Suppose you have a class which you serialized it and stored in persistence andlater modified that class to add a new field. What will happen if youdeserialize the object already serialized?

10）假设你有一个类并且已经将这个类的某一个对象序列化存储了，那么如果你在这个类中加入了新的成员变量，那么在反序列化刚才那个已经存在的对象的时候会怎么样？

这个取决于这个类是否有serialVersionUID成员。通过上面的，我们已经知道如果你的类没有提供serialVersionUID，那么编译器会自动生成，
而这个serialVersionUID就是对象的hash code值。那么如果加入新的成员变量，重新生成的serialVersionUID将和之前的不同，
那么在进行反序列化的时候就会产生java.io.InvalidClassException的异常。这就是为什么要建议为你的代码加入serialVersionUID的原因所在了。

11)JAVA反序列化时会将NULL值变成""字符!!
* */



































































































