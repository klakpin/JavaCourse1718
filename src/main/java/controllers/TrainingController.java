package controllers;

import model.pojo.Training;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.TrainingService;
import services.implementations.TrainingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/training")
public class TrainingController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(TrainingController.class);

    private final TrainingService trainingService = new TrainingServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int trainingId = Integer.parseInt(req.getParameter("id"));

        int userId = (int) req.getSession().getAttribute("userId");

        Training training = trainingService.getTraining(trainingId);

        if (training == null) {
            resp.sendError(404);
            return;
        }

        if (training.getUserId() == userId) {
            req.setAttribute("training", training);
            getServletContext().getRequestDispatcher("/training.jsp").forward(req, resp);
        } else {
            resp.sendError(403);
        }
    }

    private void deleteAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int trainingId = Integer.parseInt(req.getParameter("id"));
        if (trainingService.deleteTraining(trainingId)) {
            resp.sendRedirect("/dashboard");
        } else {
            resp.sendError(500);
        }

    }

    private void rateAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int trainigId = Integer.parseInt(req.getParameter("id"));
        int rating = Integer.parseInt(req.getParameter("rating"));
        if (trainingService.setRating(trainigId, rating)) {
            resp.sendRedirect("/training?id=" + trainigId);
        } else {
            resp.sendError(500);
        }
    }

    private void createAction(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        int userId = (int) req.getSession().getAttribute("userId");

        String date = req.getParameter("date");
        String time = req.getParameter("time");

        String[] dateArr = date.split("-");
        String[] timeArr = time.split(":");

        LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(dateArr[0]),
                Integer.parseInt(dateArr[1]),
                Integer.parseInt(dateArr[2]),
                Integer.parseInt(timeArr[0]),
                Integer.parseInt(timeArr[1]));

        Training training = new Training(-1, userId, localDateTime, -1);

        if (trainingService.addTraining(training)) {
            resp.sendRedirect("/dashboard");
        } else {
            resp.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        switch (action) {
            case "rate":
                rateAction(req, resp);
                break;
            case "delete":
                deleteAction(req, resp);
                break;
            case "create":
                createAction(req, resp);
                break;
            default:
                resp.sendError(404);
                break;
        }

    }
}
