package services;

import model.pojo.User;

public interface AuthorisationService {

    User getUser(String username, String password);
}
