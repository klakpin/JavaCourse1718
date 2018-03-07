package model.dao.interfaces;

import model.pojo.Training;

import java.util.List;

public interface TrainingDao {

    List<Training> getAllTrainings(int userId);

    boolean addTraining(Training training);

    boolean setRating(int trainingId, int score);

    Training getTrainingById(int id);

    boolean deleteTraining(int trainingId);
}
