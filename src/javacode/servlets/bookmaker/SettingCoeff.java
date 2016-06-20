package javacode.servlets.bookmaker;

import javacode.DAO.*;
import javacode.entity.Participation;
import javacode.entity.Race;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by ww on 07.06.2016.
 *
 *
 * Servlet for setting event's coefficients
 */
@WebServlet(name = "SettingCoeff",urlPatterns = "/setCoeff")
public class SettingCoeff extends HttpServlet {
    private final Logger logger = Logger.getLogger(SettingCoeff.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idrace= Integer.parseInt(request.getParameter("race"));
        int idhorse= Integer.parseInt(request.getParameter("horse"));
        try {
            MySqlDaoParticipation mySqlDaoParticipation=new MySqlDaoParticipation(MySqlDaoFactory.getConnection());
            Participation participation= mySqlDaoParticipation.getByIdRIdH(idrace,idhorse);
            participation.setWinB(Double.parseDouble(request.getParameter("winB")));
            participation.setWinL(Double.parseDouble(request.getParameter("winL")));
            participation.setPlace(Double.parseDouble(request.getParameter("place")));
            participation.setShow(Double.parseDouble(request.getParameter("showbet")));
            mySqlDaoParticipation.update(participation);
            response.sendRedirect("/setCoeff?id="+idrace);
        } catch (SQLException e) {
            logger.error("SQLException",e);
        }
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
            MySqlDaoParticipation daoParticipation=new MySqlDaoParticipation(MySqlDaoFactory.getConnection());
            List<Participation> partList=daoParticipation.getByRaceId(String.valueOf(id));
            Race race = raceDAO.getbyPK(id);
            request.setAttribute("race",race);
            request.setAttribute("partList",partList);
        } catch (SQLException e) {
            logger.error("SQLException",e);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/bookmaker_jsp/coeff.jsp").forward(request,response);
    }
}
