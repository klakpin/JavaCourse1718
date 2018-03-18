package ilya.project.spring.controller;

import ilya.project.spring.model.entities.Training;
import ilya.project.spring.model.entities.User;
import ilya.project.spring.model.entities.UserDetailsImpl;
import ilya.project.spring.model.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
public class DashboardController {


    private final TrainingService trainingService;

    @Autowired
    public DashboardController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    @RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
    public String getDashboard(Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArrayList<Training> trainings = (ArrayList<Training>) trainingService.getAllTrainingsByUserId(userDetails.getId());
        model.addAttribute("trainingsList", trainings);
        return "dashboard";
    }




}
