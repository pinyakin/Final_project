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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ww on 02.06.2016.
 *
 *
 * Servlet for adding new event
 */
@WebServlet(name = "ServletAddEvent",urlPatterns = "/addEvent")
public class ServletAddEvent extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletAddEvent.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Race race=new Race();
        race.setRacename(request.getParameter("event"));
        race.setIppodrom(request.getParameter("ippodrom"));
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date date= java.sql.Date.valueOf(request.getParameter("date"));
        race.setDates(Timestamp.valueOf(dateFormat.format(date)+" "+request.getParameter("time")+":00.000"));
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm").format(race.getDate()));
        race.setFirst("-");
        race.setSecond("-");
        race.setThird("-");
        try {
            MySqlRaceDAO raceDAO=new MySqlRaceDAO(MySqlDaoFactory.getConnection());
            raceDAO.persist(race);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        response.sendRedirect("/adminPanel?i=3");
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/admin_jsp/addEvent.jsp").forward(request,response);
    }
}