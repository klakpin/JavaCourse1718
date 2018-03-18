package ilya.project.spring.model.services;

import ilya.project.spring.model.entities.Training;

import java.sql.SQLException;
import java.util.List;

public interface TrainingService {

    List<Training> getAllTrainingsByUserId(Long id);

    Training addTraining(Training training);

    Training getTraining(Long id) throws SQLException;

    boolean deleteTraining(Long id);

    boolean setTrainingRating(Long trainingId, Integer rating);
}
