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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class FindUserServlet extends HttpServlet {

    private AtomicReference<UserDAO> dao;

    @Override
    public void init() throws ServletException {
        dao = (AtomicReference<UserDAO>) getServletContext().getAttribute("dao");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final String login = req.getParameter("login");

        final CopyOnWriteArrayList<IUser> users = new CopyOnWriteArrayList<>();
        for (IUser user: dao.get().getAll()) {
            if(user.getLogin().contains(login))
                users.add(user);
        }

        req.setAttribute("dao", users);
        req.setAttribute("findLogin", login);
        req.getRequestDispatcher(Path.ADMIN_MENU).forward(req, resp);
    }
}