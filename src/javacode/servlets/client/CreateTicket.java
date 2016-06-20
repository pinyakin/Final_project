package javacode.servlets.client;

import javacode.DAO.*;
import javacode.entity.Client;
import javacode.entity.Participation;
import javacode.entity.Race;
import javacode.entity.Ticket;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ww on 09.06.2016.
 *
 *
 * Servlet for create client ticket of bet
 */
@WebServlet(name = "CreateTicket",urlPatterns = "/ticket")
public class CreateTicket extends HttpServlet {
    private final Logger logger = Logger.getLogger(CreateTicket.class);
    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        int id= Integer.parseInt(request.getParameter("id"));
        try {
            MySqlClientDao mySqlClientDao= new MySqlClientDao(MySqlDaoFactory.getConnection());
            MySqlDaoParticipation mySqlDaoParticipation=new MySqlDaoParticipation(MySqlDaoFactory.getConnection());
            MySqlHorseDao mySqlHorseDao=new MySqlHorseDao(MySqlDaoFactory.getConnection());
            MySqlRaceDAO mySqlRaceDAO=new MySqlRaceDAO(MySqlDaoFactory.getConnection());
            MySqlTicketDao mySqlTicketDao=new MySqlTicketDao(MySqlDaoFactory.getConnection());


            Participation participation=mySqlDaoParticipation.getbyPK(id);
            Ticket ticket=new Ticket();
            Client client= (Client) request.getSession().getAttribute("user_session");
            if(client.getBalance()<Double.parseDouble(request.getParameter("money"))){
                return;
            }
            if(request.getParameter("type").equals("winB")) {
                ticket.setCoefficient(participation.getWinB());
                ticket.setTypeBet("winB");
            }
            if(request.getParameter("type").equals("winL")) {
                ticket.setCoefficient(mySqlDaoParticipation.getbyPK(id).getWinB());
                ticket.setTypeBet("winL");
            }
            if(request.getParameter("type").equals("place")) {
                ticket.setCoefficient(mySqlDaoParticipation.getbyPK(id).getWinB());
                ticket.setTypeBet("place");
            }
            if(request.getParameter("type").equals("show")) {
                ticket.setCoefficient(mySqlDaoParticipation.getbyPK(id).getWinB());
                ticket.setTypeBet("show");
            }
            ticket.setIdRace(participation.getIdrace());
            ticket.setHorse(mySqlHorseDao.getbyPK(participation.getIdhorse()).getName());
            ticket.setIdClient(client.getId());
            ticket.setDate(mySqlRaceDAO.getbyPK(participation.getIdrace()).getDate());
            ticket.setMoney(Double.parseDouble(request.getParameter("money")));
            ticket.setWin("-");
            mySqlTicketDao.persist(ticket);
            client.setBalance(client.getBalance()-ticket.getMoney());
            mySqlClientDao.update(client);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        response.sendRedirect("/ServletForFirstPage");
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id= Integer.parseInt(request.getParameter("id"));
        String type=request.getParameter("type");
        try {
            MySqlRaceDAO mySqlRaceDAO=new MySqlRaceDAO(MySqlDaoFactory.getConnection());
            MySqlDaoParticipation mySqlDaoParticipation=new MySqlDaoParticipation(MySqlDaoFactory.getConnection());
            Participation part =mySqlDaoParticipation.getbyPK(id);
            Race race=mySqlRaceDAO.getbyPK(part.getIdrace());
            request.setAttribute("race",race);
            request.setAttribute("part",part);
            request.setAttribute("type",type);
            request.getRequestDispatcher("/WEB-INF/jsp/client/ticket.jsp").forward(request,response);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }


    }
}
