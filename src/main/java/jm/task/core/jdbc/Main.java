package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        UserService service = new UserServiceImpl();

        service.createUsersTable();
        service.saveUser("Вася1", "Пупкин1", (byte) 1);
        service.saveUser("Вася2", "Пупкин2", (byte) 2);
        service.saveUser("Вася3", "Пупкин3", (byte) 3);
        service.saveUser("Вася4", "Пупкин4", (byte) 4);

        for (User user : service.getAllUsers()) {
            System.out.println(user);
        }

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
