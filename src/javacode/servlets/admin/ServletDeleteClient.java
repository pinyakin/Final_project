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
 * Servlet for delete client
 */
@WebServlet(name = "ServletDeleteClient",urlPatterns = "/delete_client")
public class ServletDeleteClient extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletDeleteClient.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email= (String) request.getAttribute("email");
        try {
            MySqlClientDao clientDao=new MySqlClientDao(MySqlDaoFactory.getConnection());
            Client client =clientDao.getClientByEmail(email);
            clientDao.delete(client);
        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
        response.sendRedirect("/adminPanel");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
