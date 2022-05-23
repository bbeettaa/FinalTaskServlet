package ua.epam.controller.filter;

import ua.epam.AppContext;
import ua.epam.controller.ViewPath;
import ua.epam.dao.UserDao;
import ua.epam.models.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.isNull;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = ((HttpServletRequest) request).getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (isNull(login) && isNull(password)) {
            login = String.valueOf(session.getAttribute("login"));
            password = String.valueOf(session.getAttribute("password"));
        }

        //@SuppressWarnings("unchecked")
        final AtomicReference<UserDao> dao = (AtomicReference<UserDao>) req.getServletContext().getAttribute("dao");

        //Logged user.
        try {
            if (dao.get().userIsExist(login, password)) {
                final Role role = dao.get().getRoleByLoginPassword(login, password);

                req.getSession().setAttribute("password", password);
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("role", role);

                String action = req.getParameter("action");
                if (action == null) action = "findUser";
                request.getRequestDispatcher("controller?action=" + action).forward(request, response);
            } else {
                moveToMenu(req, res, Role.UNREGISTERED);
            }
        } catch (Exception e){
            AppContext.LOGGER.error(e.getMessage());
            request.getRequestDispatcher(ViewPath.ERROR_PAGE).forward(request, response);
        }

    }

    /**
     * Move user to menu specified menu.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final Role role)
            throws ServletException, IOException {


        if (role.equals(Role.ADMIN)) {
            req.getRequestDispatcher("controller?action=login").forward(req, res);
            //req.getRequestDispatcher("/allUsers").forward(req, res);
        } else if (role.equals(Role.USER)) {
            req.getRequestDispatcher(ViewPath.USER_MENU).forward(req, res);
        } else {
            req.getRequestDispatcher(ViewPath.LOGIN_MENU).forward(req, res);
        }
    }


    @Override
    public void destroy() {
    }

}
