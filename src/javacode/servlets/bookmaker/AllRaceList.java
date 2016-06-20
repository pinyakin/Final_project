package javacode.servlets.bookmaker;

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
import java.util.List;

/**
 * Created by ww on 07.06.2016.
 *
 *
 * Servlet for showing bookmaker's panel
 */
@WebServlet(name = "AllRaceList",urlPatterns = "/bookmakerPanel")
public class AllRaceList extends HttpServlet {
    private final Logger logger = Logger.getLogger(AllRaceList.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        MySqlDaoFactory mySqlDaoFactory =new MySqlDaoFactory();
        MySqlRaceDAO mySqlRaceDAO;
        List<Race> list;
        try {
            //  mySqlDaoFactory.getRaceDao(mySqlDaoFactory.getConnection());
            request.getParameter("time");
            mySqlRaceDAO =new MySqlRaceDAO(mySqlDaoFactory.getConnection());
            list= mySqlRaceDAO.getTimeRace(request.getParameter("time"));

            request.setAttribute("races",list);
            request.getRequestDispatcher("/WEB-INF/jsp/bookmaker_jsp/races.jsp").forward(request,response);

        } catch (SQLException e) {
            logger.error("SQLException",e);
        }
    }
}
