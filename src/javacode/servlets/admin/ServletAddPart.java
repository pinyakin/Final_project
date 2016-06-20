package javacode.servlets.admin;

import javacode.DAO.*;
import javacode.entity.Horse;
import javacode.entity.Participation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * Created by ww on 03.06.2016.
 *
 *
 *
 * Servlet for adding new participants to event
 */
@WebServlet(name = "ServletAddPart",urlPatterns = "/addpart")
public class ServletAddPart extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletAddPart.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Participation participation=new Participation();
        final int idHorse= Integer.parseInt(request.getParameter("id"));
        final int idRace = Integer.parseInt(request.getParameter("race"));
        try {
            MySqlDaoParticipation mySqlDaoParticipation=new MySqlDaoParticipation(MySqlDaoFactory.getConnection());
            MySqlHorseDao mySqlHorseDao=new MySqlHorseDao(MySqlDaoFactory.getConnection());
            final String name=mySqlHorseDao.getbyPK(idHorse).getName();
            participation.setIdhorse(idHorse);
            participation.setIdrace(idRace);
            participation.setName(name);
            participation.setWinB(1.0);
            participation.setWinL(1.0);
            participation.setWinL(1.0);
            participation.setShow(1.0);
            mySqlDaoParticipation.persist(participation);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        response.sendRedirect("/addpart?id="+idRace);

    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LinkedList<Horse> list= new LinkedList<>();
        try {
           MySqlHorseDao mySqlHorseDao=new MySqlHorseDao(MySqlDaoFactory.getConnection());
           MySqlDaoParticipation mySqlDaoParticipation=new MySqlDaoParticipation(MySqlDaoFactory.getConnection());
           list= (LinkedList<Horse>) mySqlHorseDao.getForPart(request.getParameter("id"));
           if(list.size()==0&&mySqlDaoParticipation.getByRaceId(request.getParameter("id"))==null) {
               list = (LinkedList<Horse>) mySqlHorseDao.getAll();
           }
           request.setAttribute("list",list);
           request.setAttribute("race",request.getParameter("id"));
       } catch (SQLException e) {
            logger.error("SQLException:",e);
       }
       request.getRequestDispatcher("/WEB-INF/jsp/admin_jsp/part_to_race.jsp").forward(request,response);
    }
}
