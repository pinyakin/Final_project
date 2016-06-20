package javacode.servlets.admin;

import javacode.DAO.MySqlClientDao;
import javacode.DAO.MySqlDaoFactory;
import javacode.entity.Client;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ww on 31.05.2016.
 *
 *
 * Servlet for change user role(bookmaker,admin,client) and refill balance
 */
@WebServlet(name = "ServletControlProfile",urlPatterns = "/profControle")
public class ServletControlProfile extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletControlProfile.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        double money= Double.parseDouble(request.getParameter("money"));
        int id= Integer.parseInt(request.getParameter("id"));
        String role=request.getParameter("role");
        try {
            MySqlClientDao clientDao=new MySqlClientDao(MySqlDaoFactory.getConnection());
            Client client = clientDao.getbyPK(id);
            client.setBalance(client.getBalance()+money);
            client.setRole(role);
            clientDao.update(client);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id= Integer.parseInt(request.getParameter("id"));
        try {
            MySqlClientDao clientDao = new MySqlClientDao(MySqlDaoFactory.getConnection());
            Client client = clientDao.getbyPK(id);
            request.setAttribute("client",client);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/admin_jsp/profileview.jsp").forward(request,response);
    }
}
