package ilya.project.spring.controller;

import ilya.project.spring.model.entities.UserAlreadyExistsException;
import ilya.project.spring.model.services.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistrationController {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(RegistrationController.class.getName());

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;

    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String getRegistrationPage() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registerUser(Model model, @RequestParam String username, @RequestParam String password) {

        try {
            userService.registerNewUser(username, password);
            model.addAttribute("message", "Registration is successful.");

            log.info("New user was registered: " + username);

            return "login";
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("errorCause", e.getMessage());
            return "registration";
        }
    }
}
