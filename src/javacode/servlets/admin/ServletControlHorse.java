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
 * Servlet for delete and update horse
 */
@WebServlet(name = "ServletControlHorse",urlPatterns = "/horsecontrol")
public class ServletControlHorse extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletControlHorse.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("delete")!=null) {
            try {
                MySqlHorseDao horseDao=new MySqlHorseDao(MySqlDaoFactory.getConnection());
                Horse horse = horseDao.getbyPK(Integer.parseInt(request.getParameter("delete")));
                horseDao.delete(horse);
            } catch (SQLException e) {
                logger.error("SQLException:",e);
            }
        }
        if(request.getParameter("update")!=null){
            try {
                MySqlHorseDao horseDao=new MySqlHorseDao(MySqlDaoFactory.getConnection());
                Horse horse = horseDao.getbyPK(Integer.parseInt(request.getParameter("update")));
                horse.setName(request.getParameter("name"));
                horseDao.update(horse);
            } catch (SQLException e) {
                logger.error("SQLException:",e);
            }
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

        int id= Integer.parseInt(request.getParameter("id"));
        try {
            MySqlHorseDao horseDao=new MySqlHorseDao(MySqlDaoFactory.getConnection());
            Horse horse = horseDao.getbyPK(id);
            request.setAttribute("horse",horse);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/admin_jsp/horseview.jsp").forward(request,response);
    }
}
