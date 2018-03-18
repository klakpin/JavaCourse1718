package ilya.project.spring.model.services;

import ilya.project.spring.model.entities.User;
import ilya.project.spring.model.entities.UserAlreadyExistsException;

public interface UserService {
    User getUserByUserName(String username);

    User registerNewUser(String username, String password) throws UserAlreadyExistsException;
}
