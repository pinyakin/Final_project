package javacode.servlets.admin;

import javacode.DAO.MySqlDaoFactory;
import javacode.DAO.MySqlDaoParticipation;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ww on 05.06.2016.
 *
 *
 * Servlet for delete participant
 */
@WebServlet(name = "ServletDeletePart",urlPatterns = "/deletePart")
public class ServletDeletePart extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletDeletePart.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final int idR= Integer.parseInt(request.getParameter("race"));
        final int idH= Integer.parseInt(request.getParameter("id"));
        try {
            MySqlDaoParticipation mySqlDaoParticipation=new MySqlDaoParticipation(MySqlDaoFactory.getConnection());
            mySqlDaoParticipation.delete(mySqlDaoParticipation.getByIdRIdH(idR,idH));
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        response.sendRedirect("/eventcontrol?id="+idR);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
