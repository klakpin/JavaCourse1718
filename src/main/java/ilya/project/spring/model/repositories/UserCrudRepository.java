package ilya.project.spring.model.repositories;

import ilya.project.spring.model.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Long> {

    User getUserByUsername(String username);

    @Override
    User save(User entity);


}
