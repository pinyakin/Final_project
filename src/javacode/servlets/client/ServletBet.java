package javacode.servlets.client;

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
 * Created by ww on 09.06.2016.
 */
@WebServlet(name = "ServletBet",urlPatterns = "/bet")
public class ServletBet extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletBet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id= Integer.parseInt(request.getParameter("id"));
        try {
            MySqlRaceDAO raceDAO=new MySqlRaceDAO(MySqlDaoFactory.getConnection());
            MySqlDaoParticipation daoParticipation=new MySqlDaoParticipation(MySqlDaoFactory.getConnection());
            List<Participation> partList=daoParticipation.getByRaceId(String.valueOf(id));
            Race race = raceDAO.getbyPK(id);
            request.setAttribute("partList",partList);
            request.setAttribute("race",race);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/client/event.jsp").forward(request,response);
    }
}
