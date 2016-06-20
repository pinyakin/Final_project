package javacode.servlets.admin;

import javacode.DAO.*;
import javacode.entity.Horse;
import javacode.entity.Race;
import javacode.servlets.admin.ControlResult.Result;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ww on 03.06.2016.
 *
 *
 *
 * Servlet for update event
 */
@WebServlet(name = "ServletControlEvent",urlPatterns = "/eventcontrol")
public class ServletControlEvent extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletControlEvent.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            try {
                MySqlRaceDAO raceDAO=new MySqlRaceDAO(MySqlDaoFactory.getConnection());
                Race race = raceDAO.getbyPK(Integer.parseInt(request.getParameter("update")));
                race.setRacename(request.getParameter("racename"));
                race.setIppodrom(request.getParameter("hippodrome"));
                race.setDates(Timestamp.valueOf(request.getParameter("date")));
                race.setFirst(request.getParameter("first"));
                race.setSecond(request.getParameter("second"));
                race.setThird(request.getParameter("third"));
                raceDAO.update(race);
                if(!race.getFirst().equals("-")&&!race.getSecond().equals("-")&&!race.getThird().equals("-")){
                    Result.control(race.getId());
                }
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

        int id= Integer.parseInt(request.getParameter("id"));
        try {

            MySqlRaceDAO raceDAO=new MySqlRaceDAO(MySqlDaoFactory.getConnection());
            MySqlHorseDao horseDao=new MySqlHorseDao(MySqlDaoFactory.getConnection());
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            String date=dateFormat.format(new Date());
            List<Horse> list= horseDao.getPartByIdRace(id);
            Race race = raceDAO.getbyPK(id);
            request.setAttribute("race",race);
            request.setAttribute("date",date);
            request.setAttribute("list",list);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/admin_jsp/eventview.jsp").forward(request,response);
    }
}