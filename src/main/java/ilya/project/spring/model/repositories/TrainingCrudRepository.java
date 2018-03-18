package ilya.project.spring.model.repositories;

import ilya.project.spring.model.entities.Training;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingCrudRepository extends CrudRepository<Training, Long> {

    List<Training> getAllByUserId(Long userId);

    @Override
    Training save(Training entity);

    Training getById(Long id);

    @Override
    void deleteById(Long id);
}
