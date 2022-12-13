package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    public static Connection connection;


    public static Connection getConnection() {
//        try {
//
//            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//
//
//            if (!connection.isClosed()) {
//                System.out.println("БД работает");
//            }
//            connection.close();
//        } catch (SQLException e) {
//            System.out.println("БД НЕ работает");
//        }
//        return connection;

        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


}























