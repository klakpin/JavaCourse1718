package ilya.project.spring.model.entities;

public class UserAlreadyExistsException extends Exception {


    public UserAlreadyExistsException(String s) {
        super(s);
    }
}
