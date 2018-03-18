package ilya.project.spring.controller;

import ilya.project.spring.model.entities.Training;
import ilya.project.spring.model.entities.UserDetailsImpl;
import ilya.project.spring.model.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Controller
public class TrainingController {

    private static final java.util.logging.Logger log = java.util.logging.Logger.getLogger(TrainingController.class.getName());


    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping(value = "/training/{id}", method = RequestMethod.GET)
    public String getTrainingInfo(Model model, @PathVariable("id") Long id) {
        try {
            Training training = trainingService.getTraining(id);
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (training.getUserId().equals(userDetails.getId())) {
                model.addAttribute("training", training);
                return "training";
            }
            model.addAttribute("message", "Wrong training id");
        } catch (SQLException e) {
            model.addAttribute("message", "Training not found");
        }
        return "dashboard";
    }

    @RequestMapping(value = "/training/add", method = RequestMethod.POST)
    public String addTraining(@RequestParam String date, @RequestParam String time, Model model) {

        String[] dateArr = date.split("-");
        String[] timeArr = time.split(":");

        LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(dateArr[0]),
                Integer.parseInt(dateArr[1]),
                Integer.parseInt(dateArr[2]),
                Integer.parseInt(timeArr[0]),
                Integer.parseInt(timeArr[1]));

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Training training = new Training(userDetails.getId(), localDateTime, -1);

        training = trainingService.addTraining(training);

        if (training != null) {
            return "redirect:/training/" + training.getId();
        } else {
            model.addAttribute("message", "Error while training creation");
            return "dashboard";
        }
    }

    @RequestMapping(value = "/training/rate", method = RequestMethod.POST)
    public String rateTraining(@RequestParam Long trainingId, @RequestParam int trainingRating, Model model) {
        if (trainingService.setTrainingRating(trainingId, trainingRating)) {
            return "redirect:/training/" + trainingId;
        } else {
            model.addAttribute("message", "Error while changing training rating");
            return "dashboard";
        }
    }

    @RequestMapping(value = "/training/delete", method = RequestMethod.POST)
    public String deleteTraining(@RequestParam Long trainingId, Model model) {

        try {
            Training training = trainingService.getTraining(trainingId);
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (training.getUserId().equals(userDetails.getId())) {
                boolean result = trainingService.deleteTraining(trainingId);
                if (result) {
                    model.addAttribute("message", "Done");
                } else {
                    model.addAttribute("message", "Problems with training deletion");
                }
            } else {
                log.info("There was try for illegal access from user " + userDetails.getId());
                model.addAttribute("message", "Illegal access");
            }
        } catch (SQLException e) {
            model.addAttribute("message", "Training not found");
        }

        return "redirect:/dashboard";

    }
}
