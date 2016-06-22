package javacode.servlets.admin;

import javacode.DAO.MySqlDaoFactory;
import javacode.DAO.MySqlHorseDao;
import javacode.entity.Horse;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ww on 02.06.2016.
 *
 *
 * Servlet for adding new horses
 */
@WebServlet(name = "ServletAddHorse",urlPatterns = "/addHorse")
public class ServletAddHorse extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletAddHorse.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("name")==null){
            logger.error("Troubles with parameters");
            response.sendError(400);
            return;
        }
        if(request.getParameter("name").trim().isEmpty()){
            logger.debug("Some empty fields");
            request.setAttribute("messageNewHorse","empty");
            doGet(request, response);
            return;
        }
        if(request.getParameter("name").length()>100) {
            logger.debug("Too long value");
            request.setAttribute("messageNewHorse","tooLong");
            doGet(request, response);
            return;
        }

        final String name=request.getParameter("name");
        Horse horse=new Horse();
        horse.setName(name);
        try {
            MySqlHorseDao mySqlHorseDao=new MySqlHorseDao(MySqlDaoFactory.getConnection());
            if(mySqlHorseDao.getByName(name).size()!=0){
                logger.debug("That name is already used");
                request.setAttribute("messageNewHorse","nameUsed");
                doGet(request, response);
                return;
            }
            mySqlHorseDao.persist(horse);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        response.sendRedirect("/adminPanel?i=2");
    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/jsp/admin_jsp/addHorse.jsp").forward(request,response);
    }
}
