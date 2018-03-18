package ilya.project.spring.model.services;

import ilya.project.spring.model.entities.Training;
import ilya.project.spring.model.repositories.TrainingCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainingServiceImpl implements TrainingService {


    private final TrainingCrudRepository trainingCrudRepository;

    @Autowired
    public TrainingServiceImpl(TrainingCrudRepository trainingCrudRepository) {
        this.trainingCrudRepository = trainingCrudRepository;
    }

    @Override
    public List<Training> getAllTrainingsByUserId(Long id) {
        List<Training> trainings = trainingCrudRepository.getAllByUserId(id);
        if (trainings == null) {
            return new ArrayList<>();
        } else {
            return trainings;
        }
    }

    @Override
    public Training getTraining(Long id) throws SQLException {
        Training training = trainingCrudRepository.getById(id);
        if (training == null) {
            throw new SQLException("Training with id " + id + " not found");
        }
        return training;
    }

    @Override
    public Training addTraining(Training training) {
        return trainingCrudRepository.save(training);
    }

    @Override
    public boolean setTrainingRating(Long trainingId, Integer rating) {
        Training training = trainingCrudRepository.getById(trainingId);

        if (training == null) {
            return false;
        }

        training.setRating(rating);

        return trainingCrudRepository.save(training) != null;
    }

    @Override
    public boolean deleteTraining(Long id) {
        trainingCrudRepository.deleteById(id);

        return trainingCrudRepository.getById(id) == null;
    }
}
