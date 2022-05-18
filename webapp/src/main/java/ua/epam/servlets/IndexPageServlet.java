package ua.epam.servlets;

import ua.epam.dao.UserDAO;
import ua.epam.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;



public class IndexPageServlet extends HttpServlet {
    private AtomicReference<UserDAO> dao;
    private Map<Integer, User> users;
    @Override
    public void init() throws ServletException {
        dao = (AtomicReference<UserDAO>) getServletContext().getAttribute("dao");
        users = (Map<Integer, User>) getServletContext().getAttribute("users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        req.setAttribute("dao", dao.get().getAll());
        req.setAttribute("users", users.values());
        req.getRequestDispatcher("WEB-INF/view/admin_menu.jsp").forward(req, resp);
    }

}