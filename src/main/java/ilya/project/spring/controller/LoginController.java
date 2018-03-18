package ilya.project.spring.controller;

import ilya.project.spring.model.repositories.UserCrudRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

//    private final UserCrudRepository userRepository;
//
//    @Autowired
//    public LoginController(UserCrudRepository userRepository) {
//        this.userRepository = userRepository;
//    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }
}
