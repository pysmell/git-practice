package javabase.序列化;

import java.io.*;

/**
 * transient使用细节——被transient关键字修饰的变量真的不能被序列化吗？
 */
public class ExternalizableTest implements Externalizable {

    private transient String content = "是的，我将会被序列化，不管我是否被transient关键字修饰";

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(content);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        content = (String) in.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ExternalizableTest externalizableTest = new ExternalizableTest();
        ObjectOutput objectOutput = new ObjectOutputStream(new FileOutputStream(new File("F:test")));
        objectOutput.writeObject(externalizableTest);

        ObjectInput objectInput = new ObjectInputStream(new FileInputStream(new File("F:test")));

        externalizableTest = (ExternalizableTest) objectInput.readObject();
        System.out.println(externalizableTest.content);

        objectOutput.close();
        objectInput.close();
    }
}
/**
 * 这是为什么呢，不是说类的变量被transient关键字修饰以后将不能序列化了吗？
 *
 * 我们知道在Java中，对象的序列化可以通过实现两种接口来实现，若实现的是Serializable接口，
 * 则所有的序列化将会自动进行，若实现的是Externalizable接口，则没有任何东西可以自动序列化，
 * 需要在writeExternal方法中进行手工指定所要序列化的变量，这与是否被transient修饰无关。
 * 因此第二个例子输出的是变量content初始化的内容，而不是null。
 */





























