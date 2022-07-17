package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

public class Util {

    public Optional<Connection> createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return Optional.of(
                    DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/user",
                    "root",
                    "root"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
