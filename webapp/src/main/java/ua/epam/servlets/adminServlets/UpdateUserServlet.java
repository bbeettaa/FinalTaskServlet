package ua.epam.servlets.adminServlets;

import ua.epam.Path;
import ua.epam.dao.UserDAO;
import ua.epam.models.IUser;
import ua.epam.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class UpdateUserServlet extends HttpServlet {

    private AtomicReference<UserDAO> dao;

    @Override
    public void init() throws ServletException {
        dao = (AtomicReference<UserDAO>) getServletContext().getAttribute("dao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        final String id = req.getParameter("id");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

        final IUser user = dao.get().getById(Integer.parseInt(id));
        user.setLogin(login);
        user.setPassword(password);

        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String id = req.getParameter("id");

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");

/*        if (UsersUtils.idIsInvalid(id, users)) {
            resp.sendRedirect(req.getContextPath() + "/");
            return;
        }*/
        final IUser user = dao.get().getById(Integer.parseInt(id));
        req.setAttribute("user", user);

        req.getRequestDispatcher(Path.UPDATE_USER_MENU)
                .forward(req, resp);
    }
}
