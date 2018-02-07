package interfaces;

import java.util.List;

public interface Room {
    List<User> getUsers();

    void addUser(User user);
}
