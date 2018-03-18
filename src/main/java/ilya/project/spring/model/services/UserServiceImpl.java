package ilya.project.spring.model.services;

import ilya.project.spring.model.entities.User;
import ilya.project.spring.model.entities.UserAlreadyExistsException;
import ilya.project.spring.model.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserCrudRepository userCrudRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserCrudRepository userCrudRepository, PasswordEncoder passwordEncoder) {
        this.userCrudRepository = userCrudRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUserByUserName(String username) {
        return userCrudRepository.getUserByUsername(username);
    }

    @Override
    public User registerNewUser(String username, String password) throws UserAlreadyExistsException {

        if (userCrudRepository.getUserByUsername(username) != null) {
            throw new UserAlreadyExistsException("User with username " + username + " already exists.");
        }

        User user = new User(username, passwordEncoder.encode(password));
        return userCrudRepository.save(user);
    }
}
