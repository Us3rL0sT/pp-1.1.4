package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static jm.task.core.jdbc.util.Util.connection;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(30) NOT NULL, " +
                "lastname VARCHAR(30) NOT NULL, " +
                "age INT NOT NULL, PRIMARY KEY(id))";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        String SQLdropTable = "DROP TABLE IF EXISTS users";
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.executeUpdate(SQLdropTable);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM `users` WHERE `id`=?";


        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        String sql = "SELECT name, lastname, age FROM users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);

            ResultSet rs = preparedStatement.executeQuery(sql);

            while (rs.next()) {

                User user = new User(rs.getString(1),
                        rs.getString(2),
                        rs.getByte(3));
                userList.add(user);
            }
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return userList;

    }

    public void cleanUsersTable() {
        String SQLdeleteAllUser = "TRUNCATE TABLE users";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQLdeleteAllUser)){
            connection.setAutoCommit(false);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}





























