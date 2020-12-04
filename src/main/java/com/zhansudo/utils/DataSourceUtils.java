package com.zhansudo.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceUtils {
    private static DataSource dataSource = new ComboPooledDataSource();
    private static ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = t1.get();
        if (connection == null) {
            connection = dataSource.getConnection();
            t1.set(connection);
        }
        return connection;
    }

    /**
     * 开启事务
     */
    public static void startTransaction() throws SQLException {
        Connection connection = getConnection();
        if (connection != null)
            connection.setAutoCommit(false);
    }

    /**
     * 提交并结束事务
     */
    public static void releaseAndCloseConnection() throws SQLException {
        Connection connection = getConnection();
        if (connection != null) {
            connection.commit();
            t1.remove();
            connection.close();
        }
    }

    /**
     * 事务回滚
     */
    public static void rollback() throws SQLException {
        Connection connection = getConnection();
        if (connection != null)
            connection.rollback();
    }
}
