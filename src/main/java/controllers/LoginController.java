package controllers;

import model.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.AuthorisationService;
import services.implementations.AuthorisationServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private final AuthorisationService authorisationService = new AuthorisationServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = authorisationService.getUser(login, password);

        if (user != null) {
            req.getSession().setAttribute("isAuthorised", true);
            req.getSession().setAttribute("userId", user.getId());
            resp.sendRedirect("/dashboard");
        } else {
            resp.sendRedirect("/login.jsp?message=wrongPassword");
        }
    }
}
