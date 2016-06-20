package javacode.servlets.admin;

import javacode.DAO.MySqlDaoFactory;
import javacode.DAO.MySqlRaceDAO;
import javacode.entity.Race;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ww on 08.06.2016.
 *
 *
 * Servlet for delete event
 */
@WebServlet(name = "ServletDeleteEvent",urlPatterns = "/deleteEvent")
public class ServletDeleteEvent extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletDeleteEvent.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                MySqlRaceDAO raceDAO=new MySqlRaceDAO(MySqlDaoFactory.getConnection());
                Race race = raceDAO.getbyPK(Integer.parseInt(request.getParameter("delete")));
                raceDAO.delete(race);
            } catch (SQLException e) {
                logger.error("SQLException:",e);
            }
        response.sendRedirect("/adminPanel?i=3");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
