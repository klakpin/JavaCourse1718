package controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = {"/dashboard", "/training", "/"})
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        try {
            if ((boolean) req.getSession().getAttribute("isAuthorised")) {
                chain.doFilter(request, response);
            }
        } catch (NullPointerException e) {
            request.getServletContext().getRequestDispatcher("/login").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
