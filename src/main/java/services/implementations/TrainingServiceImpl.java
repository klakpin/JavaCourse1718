package services.implementations;

import model.dao.implementations.TrainingDaoImpl;
import model.dao.interfaces.TrainingDao;
import model.pojo.Training;
import services.TrainingService;

import java.util.List;

public class TrainingServiceImpl implements TrainingService {

    private static final TrainingDao trainingDao = new TrainingDaoImpl();

    @Override
    public Training getTraining(int id) {
        return trainingDao.getTrainingById(id);
    }

    @Override
    public List<Training> getAllTrainings(int userId) {
        return trainingDao.getAllTrainings(userId);
    }

    @Override
    public boolean addTraining(Training training) {
        return trainingDao.addTraining(training);
    }

    @Override
    public boolean setRating(int trainingId, int rating) {
        return trainingDao.setRating(trainingId, rating);
    }

    @Override
    public boolean deleteTraining(int trainingId) {
        return trainingDao.deleteTraining(trainingId);
    }
}
