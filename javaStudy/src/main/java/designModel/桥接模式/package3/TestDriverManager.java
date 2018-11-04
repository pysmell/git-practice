package designModel.桥接模式.package3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class TestDriverManager {

    public void testConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        //一个参数
        // DriverManager.getConnection("com.mysql.jdbc.Driver");
        //两个参数
        Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", createPropeties());
        //三个参数
        // Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "root");

        Statement statement = connection.createStatement();
        ResultSet query = statement.executeQuery("select * from test;");
        while (query.next()) {
            System.out.println(query.getInt(1));
        }
    }

    /**
     * 用于声明一个存储用户名和密码的Properties对象
     * @return Properties
     */
    public Properties createPropeties(){
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        return properties;
    }

    public static void main(String[] args) throws Exception {
        new TestDriverManager().testConnection();
    }
}
