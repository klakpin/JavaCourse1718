package model.dao.implementations;

import model.dao.interfaces.TrainingDao;
import model.pojo.Training;
import model.utils.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TrainingDaoImpl implements TrainingDao {

    private static final Logger LOG = LoggerFactory.getLogger(TrainingDaoImpl.class);

    @Override
    public List<Training> getAllTrainings(int userId) {
        List<Training> trainings = new ArrayList<>();

        String sql = "SELECT * FROM trainings WHERE userid = ?";

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                trainings.add(new Training(resultSet.getInt("id"),
                        resultSet.getInt("userid"),
                        resultSet.getObject("trainingdatetime", LocalDateTime.class),
                        resultSet.getInt("rating")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("SQL exception while proceeding query " + sql, e);
        }
        return trainings;
    }

    @Override
    public boolean addTraining(Training training) {

        boolean result = false;

        String sql = "INSERT INTO trainings(userid, trainingdatetime, rating) VALUES (?, ?, ?);";

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, training.getUserId());
            statement.setObject(2, training.getDateTime());
            statement.setInt(3, training.getRating());

            if (statement.executeUpdate() == 1) {
                result = true;
            }

            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("SQL exception while proceeding query " + sql, e);
        }
        return result;
    }

    @Override
    public boolean setRating(int trainingId, int score) {

        boolean result = false;

        String sql = "UPDATE trainings SET rating = ? where id = ?";

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, score);
            statement.setInt(2, trainingId);

            if (statement.executeUpdate() == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("SQL exception while proceeding query " + sql, e);
        }
        return result;
    }

    @Override
    public Training getTrainingById(int id) {

        Training training = null;
        String sql = "SELECT * FROM trainings WHERE id = ?";


        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                training = new Training(resultSet.getInt("id"),
                        resultSet.getInt("userid"),
                        resultSet.getObject("trainingdatetime", LocalDateTime.class),
                        resultSet.getInt("rating"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("SQL exception while proceeding query " + sql, e);

        }

        return training;
    }

    @Override
    public boolean deleteTraining(int trainingId) {

        boolean success = false;
        String sql = "DELETE FROM trainings WHERE id = ?";

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setObject(1, trainingId);
            if (statement.executeUpdate() == 1) {
                success = true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("SQL exception while proceeding query " + sql, e);
        }

        return success;
    }
}
