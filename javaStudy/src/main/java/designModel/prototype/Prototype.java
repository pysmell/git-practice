package designModel.prototype;

import java.io.*;

/**
 * 原型设计模式
 */
public class Prototype {

    private String string;

    private SerializableObject obj;

    /* 浅复制 */
    public Object clone()throws CloneNotSupportedException{
        Prototype proto=(Prototype)super.clone();
        return proto;
    }

    /* 深复制 */
    public Object deepClone()throws IOException,ClassNotFoundException{

        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois=new ObjectInputStream(bis);
        return ois.readObject();
    }

    public String getString(){
        return string;
    }

    public void setString(String string){
        this.string=string;
    }

    public SerializableObject getObj(){
        return obj;
    }

    public void setObj(SerializableObject obj){
        this.obj=obj;
    }

}

class SerializableObject implements Serializable {
    private static final long serialVersionUID = 1L;
}

