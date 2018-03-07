package services;

import model.pojo.Training;

import java.util.List;

public interface TrainingService {
    Training getTraining(int id);

    List<Training> getAllTrainings(int userId);

    boolean addTraining(Training training);

    boolean setRating(int id, int rating);

    boolean deleteTraining(int trainingId);
}
