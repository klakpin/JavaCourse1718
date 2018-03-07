package model.dao.interfaces;

import model.pojo.User;

public interface UserDao {
    User getUserByLoginAndPassword(String name, String password);
}
