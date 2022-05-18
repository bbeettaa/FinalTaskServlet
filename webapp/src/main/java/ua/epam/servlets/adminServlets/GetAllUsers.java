package ua.epam.servlets.adminServlets;

import ua.epam.Path;
import ua.epam.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class GetAllUsers extends HttpServlet {
    private AtomicReference<UserDAO> dao;
    @Override
    public void init() throws ServletException {
        dao = (AtomicReference<UserDAO>) getServletContext().getAttribute("dao");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("dao", dao.get().getAll());
        req.getRequestDispatcher(Path.ADMIN_MENU).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("dao", dao.get().getAll());
        req.getRequestDispatcher(Path.ADMIN_MENU).forward(req, resp);
    }

}