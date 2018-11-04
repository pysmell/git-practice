package designModel.享元模式;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class ConnectionPool {

    private Vector<Connection> pool;

    /**
     * 公有属性
     */
    private String url = "jdbc:mysql://localhost:3306/test";
    private String username = "root";
    private String password = "root";

    /**
     * Connection服务提供者
     */
    private String driverClassName = "com.mysql.jdbc.Driver";

    private int poolSize = 100;

    Connection connection = null;

    private ConnectionPool() {
        pool = new Vector<>(poolSize);

        for (int i = 0; i < poolSize; i++) {
            try {
                //将其自身加到DriverManager的提供者集合中
                Class.forName(driverClassName);
                connection = DriverManager.getConnection(url, username, password);
                pool.add(connection);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 返回连接到连接池
     */
    public synchronized void release(Connection connection) {
        pool.add(connection);
    }

    /**
     * 返回连接池中的一个数据库连接
     */
    public synchronized Connection getConnection() {
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        } else {
            return null;
        }
    }

    /**
     * 静态方法实现单例
     */
    private static class SingletonFactory {
        private static ConnectionPool singleton = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return SingletonFactory.singleton;
    }

}





























