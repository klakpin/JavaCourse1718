package model.dao.implementations;

import model.dao.interfaces.UserDao;
import model.pojo.User;
import model.utils.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class UserDaoImpl implements UserDao {

    private static final Logger LOG = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User getUserByLoginAndPassword(String name, String password) {

        User user = null;
        String sql = "SELECT * FROM users WHERE name = ? and password = ?;";

        try (Connection connection = DataSourceFactory.getDataSource().getConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userName = resultSet.getString("name");
                String userPassword = resultSet.getString("password");
                String userId = resultSet.getString("id");
                user = new User(userName, userPassword, Integer.valueOf(userId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("SQL exception while proceeding query " + sql, e);
        }

        return user;
    }
}
