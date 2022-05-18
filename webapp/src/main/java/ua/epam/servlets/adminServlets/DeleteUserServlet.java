package ua.epam.servlets.adminServlets;

import ua.epam.dao.UserDAO;
import ua.epam.users.utils.UsersUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class DeleteUserServlet extends HttpServlet {
    private AtomicReference<UserDAO> dao;

    @Override
    public void init() throws ServletException {
        dao = (AtomicReference<UserDAO>) getServletContext().getAttribute("dao");
/*        if (users == null || !(users instanceof ConcurrentHashMap)) {
            throw new IllegalStateException("You're repo does not initialize!");
        } else {
            this.users = (ConcurrentHashMap<Integer, User>) users;
        }*/
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (UsersUtils.idIsNumber(req)) {
            dao.get().deleteById(Integer.valueOf(req.getParameter("id")));
        }
        resp.sendRedirect(req.getContextPath() + "/");
    }
}