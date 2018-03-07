package services.implementations;

import model.dao.implementations.UserDaoImpl;
import model.dao.interfaces.UserDao;
import model.pojo.User;
import services.AuthorisationService;

public class AuthorisationServiceImpl implements AuthorisationService{

    private static final UserDao userDao = new UserDaoImpl();

    @Override
    public User getUser(String username, String password) {
        return userDao.getUserByLoginAndPassword(username, password);
    }
}
