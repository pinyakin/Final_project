package javacode.servlets;

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
 * Created by ww on 02.05.2016.
 *
 *
 * Servlet for get 10 next events
 */
@WebServlet(name = "ServletForFirstPage", urlPatterns = "/ServletForFirstPage")
public class ServletForFirstPage extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletForFirstPage.class);
    public ServletForFirstPage() {
        super();
    }

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
            mySqlRaceDAO =new MySqlRaceDAO(mySqlDaoFactory.getConnection());
            list= mySqlRaceDAO.getFutureRace();
            request.setAttribute("races",list);
            request.getRequestDispatcher("/WEB-INF/jsp/shared_jsp/index.jsp").forward(request,response);

        } catch (SQLException e) {
            logger.error("Exception in getConnection",e);
        }

    }
}
