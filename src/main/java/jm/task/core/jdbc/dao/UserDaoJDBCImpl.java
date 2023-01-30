
package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//import static com.mysql.cj.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {

    private static final Connection conn = Util.getInstance().getConnection();


    public UserDaoJDBCImpl() {

    }

    // Создание таблицы для User(ов) – не должно приводить к исключению, если такая таблица уже существует
    public void createUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Удаление таблицы User(ов) – не должно приводить к исключению, если таблицы не существует
    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Добавление User в таблицу
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement pstm = conn.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            conn.setAutoCommit(false);
            pstm.setString(1, name);
            pstm.setString(2, lastName);
            pstm.setByte(3, age);
            pstm.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    // Удаление User из таблицы ( по id )
    public void removeUserById(long id)  {
        try (PreparedStatement pstm = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {

            pstm.setLong(1, id);
            pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Получение всех Users из таблицы
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM users")) {
            conn.setAutoCommit(false);
            while(resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                users.add(user);
            conn.commit();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }

        return users;
    }

    // Очистка содержания таблицы
    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
