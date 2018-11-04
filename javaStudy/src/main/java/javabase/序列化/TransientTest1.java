package javabase.序列化;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;

public class TransientTest1 {

    public static void main(String[] args) {

        User1 user = new User1();
        user.setUserName("linqw");
        user.setPassword("382394323");
        System.out.println("read before Serializable ");
        System.out.println("userName: " + user.getUserName());
        System.out.println("password：" + user.getPassword());

        try {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("F:/user.txt"));
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            //在反序列化之前
            User1.userName = "hll";

            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "F:/user.txt"));
            // 从流中读取User的数据
            user = (User1) is.readObject();
            is.close();

            System.out.println("read after Serializable: ");
            System.out.println("username: " + user.getUserName());
            System.err.println("password: " + user.getPassword());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}

class User1 implements Serializable {

    protected static String userName;

    private String password;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        User1.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
/**
 * username被定义为静态，反序列化后是linqw，这不与static属性不会参加序列化矛盾么？
 * 实际上反序列化后类中static型变量username的值为当前JVM中对应static变量的值，这个值是JVM中的不是反序列化得出的
 */




























