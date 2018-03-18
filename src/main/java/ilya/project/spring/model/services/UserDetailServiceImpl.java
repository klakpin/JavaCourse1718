package ilya.project.spring.model.services;

import ilya.project.spring.model.entities.User;
import ilya.project.spring.model.entities.UserDetailsImpl;
import ilya.project.spring.model.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserCrudRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username " + username + " was not found");
        } else {
            return new UserDetailsImpl(user);
        }
    }
}
