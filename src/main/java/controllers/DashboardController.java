package controllers;

import model.pojo.Training;
import services.TrainingService;
import services.implementations.TrainingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/dashboard", ""})
public class DashboardController extends HttpServlet {

    private final TrainingService trainingService = new TrainingServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String userId = String.valueOf(session.getAttribute("userId"));

        List<Training> trainings = trainingService.getAllTrainings(Integer.parseInt(userId));

        req.setAttribute("trainings", trainings);
        getServletContext().getRequestDispatcher("/dashboard.jsp").forward(req, resp);

    }
}
