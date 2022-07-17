package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private final Util util;

    public UserDaoJDBCImpl() {
        this.util = new Util();
    }

    public void createUsersTable() {
        Optional<Connection> connectionOptional = util.createConnection();
        String sql = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                "  `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
                "  `name` varchar(255) COLLATE 'utf8_general_ci' NOT NULL,\n" +
                "  `lastName` varchar(255) COLLATE 'utf8_general_ci' NOT NULL,\n" +
                "  `age` int NOT NULL\n" +
                ") ENGINE='InnoDB' COLLATE 'utf8_general_ci';";
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                statement.executeUpdate(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Optional<Connection> connectionOptional = util.createConnection();
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                statement.executeUpdate("DROP TABLE IF EXISTS user");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Optional<Connection> connectionOptional = util.createConnection();
        String sql = "INSERT INTO `user` (`name`, `lastName`, `age`) VALUES (?, ?, ?);";
        if (connectionOptional.isPresent()) {
            try (PreparedStatement ps = connectionOptional.get().prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, lastName);
                ps.setLong(3, age);
                System.out.println("User с именем " + name + " добавлен в таблицу");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Optional<Connection> connectionOptional = util.createConnection();
        String sql = "DELETE FROM user WHERE id = ?";
        if (connectionOptional.isPresent()) {
            try (PreparedStatement ps = connectionOptional.get().prepareStatement(sql)) {
                ps.setLong(1, id);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Optional<Connection> connectionOptional = util.createConnection();
        String sql = "SELECT id, name, lastName, age FROM user";
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);
                List<User> result = new ArrayList<>();
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    result.add(user);
                }
                return result;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    public void cleanUsersTable() {
        Optional<Connection> connectionOptional = util.createConnection();
        String sql = "TRUNCATE TABLE user";
        if (connectionOptional.isPresent()) {
            try (Statement statement = connectionOptional.get().createStatement()) {
                statement.executeUpdate(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
