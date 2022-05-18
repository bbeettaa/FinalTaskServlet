package ua.epam.servlets.adminServlets;

import ua.epam.dao.UserDAO;
import ua.epam.models.Role;
import ua.epam.models.User;
import ua.epam.users.utils.UserValidator.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AddUserServlet extends HttpServlet {

    private AtomicReference<UserDAO> dao;

    private AtomicInteger id;

    @Override
    public void init() throws ServletException {
        dao = (AtomicReference<UserDAO>) getServletContext().getAttribute("dao");
        id = new AtomicInteger(2);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //if (UsersUtils.requestIsValid(req)) {

            final String login = req.getParameter("login");
            final String password = req.getParameter("password");
            final Role role = Role
                    .valueOf(Role.ADMIN.getDeclaringClass(),
                            req.getParameter("role").toUpperCase());

            final User user = new User();
            final int id = this.id.getAndIncrement();
            user.setId(id);
            user.setPassword(password);
            user.setLogin(login);
            user.setRole(role);

      //  }
        if(UserValidator.validate(user)){
        dao.get().add(user);
        resp.sendRedirect(req.getContextPath() + "/");
    }}
}