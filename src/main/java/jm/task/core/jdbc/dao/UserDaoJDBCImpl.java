package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private String sql;
    private static final Statement statement = null;
    Connection connection = null;

    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {

        sql = "CREATE TABLE IF NOT EXISTS users(" +
                "id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(30) NOT NULL, " +
                "lastname VARCHAR(30) NOT NULL, " +
                "age INT NOT NULL, PRIMARY KEY(id))";
        try (Connection connection = Util.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";


        try (Connection connection = Util.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    @Override
    public void removeUserById(long id) {

        sql = "DELETE FROM `users` WHERE `id`=?";


        try (Connection connection = Util.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    @Override
    public List<User> getAllUsers() {


        List<User> userList = new ArrayList<>();
        sql = "SELECT name, lastname, age FROM users";
        try (Connection connection = Util.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            ResultSet rs = preparedStatement.executeQuery(sql);

            while (rs.next()) {

                User user = new User(rs.getString(1),
                        rs.getString(2),
                        rs.getByte(3));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return userList;

    }
    @Override
    public void cleanUsersTable() {

        sql = "TRUNCATE TABLE users";
        try (Connection connection = Util.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}





























