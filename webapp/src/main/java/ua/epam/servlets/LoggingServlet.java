package ua.epam.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LoggingServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(LoggingServlet.class);

    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        try {
            logger.info("invoked the LoggingServlet...");
            writer.println("Check your web server console...");
            writer.flush();
            writer.close();
        } finally {
            writer.close();
        }
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)

            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)

            throws ServletException, IOException {
        processRequest(request, response);
    }
}