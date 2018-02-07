package implementations;

import interfaces.Room;
import interfaces.User;

import java.util.ArrayList;
import java.util.List;

public class RoomImpl implements Room {

    private ArrayList<User> users = new ArrayList<>();

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }
}
