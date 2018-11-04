package javabase.序列化;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;

/**
 * @description 使用transient关键字不序列化某个变量
 *        注意读取的时候，读取数据的顺序一定要和存放数据的顺序保持一致
 */
public class TransientTest {

    public static void main(String[] args) {

        User user = new User();
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
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "F:/user.txt"));
            // 从流中读取User的数据
            user = (User) is.readObject();
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

@Setter
@Getter
@ToString
class User implements Serializable {

    private String userName;

    private transient String password;

}

/**
 * transient使用小结
 * 1）一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量在序列化后无法获得访问
 * 2）transient关键子只能变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则
 * 该类需要实现Serializable接口
 * 3）被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修辞，均不能被序列化
 */






























