package ua.epam.controller.controller;

import ua.epam.controller.commands.CommandFactory;
import ua.epam.controller.commands.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Main controller
 */
public class Controller extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        CommandFactory commandFactory = CommandFactory.commandFactory();
        ICommand ic = commandFactory.getCommand(req);
        String page = ic.execute(req, resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher(page);
        //req.setAttribute("login",req.getAttribute("login"));
        if (!page.equals("redirect")) {
            dispatcher.forward(req, resp);
        }
    }
}
