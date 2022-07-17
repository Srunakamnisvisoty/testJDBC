package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user",
                    "root",
                    "root");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
