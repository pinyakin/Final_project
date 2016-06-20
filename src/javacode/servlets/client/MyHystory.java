package javacode.servlets.client;

import javacode.DAO.MySqlDaoFactory;
import javacode.DAO.MySqlRaceDAO;
import javacode.DAO.MySqlTicketDao;
import javacode.entity.Client;
import javacode.entity.Ticket;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ww on 05.06.2016.
 *
 *
 *
 * Servlet for take Client's history
 */
@WebServlet(name = "MyHystory",urlPatterns = "/myhistory")
public class MyHystory extends HttpServlet {
    private final Logger logger = Logger.getLogger(MyHystory.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            MySqlRaceDAO mySqlRaceDAO=new MySqlRaceDAO(MySqlDaoFactory.getConnection());
            MySqlTicketDao mySqlTicketDao=new MySqlTicketDao(MySqlDaoFactory.getConnection());
            Client client= (Client) request.getSession().getAttribute("user_session");
            List<Ticket> list=mySqlTicketDao.getClientHistory(client);
            List<String> raceName=new ArrayList<>();
            for(int i=0;i<list.size();i++)
            {
                raceName.add(mySqlRaceDAO.getbyPK(list.get(i).getIdRace()).getRacename());
            }
            request.setAttribute("list",list);
            request.setAttribute("raceName",raceName);
            request.getRequestDispatcher("/WEB-INF/jsp/client/myhistory.jsp").forward(request,response);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
    }
}
