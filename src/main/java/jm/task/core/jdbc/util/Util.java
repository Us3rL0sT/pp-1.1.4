package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;


    public static Connection getConnection() {

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                connection.setAutoCommit(false);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


}























