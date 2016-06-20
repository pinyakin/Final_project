package javacode.servlets.client;

import javacode.DAO.MySqlClientDao;
import javacode.DAO.MySqlDaoFactory;
import javacode.entity.Client;
import javacode.servlets.security.EncodingPassword;
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
 */
@WebServlet(name = "ServletForProfile",urlPatterns = "/profile")
public class ServletForProfile extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletForProfile.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       String email= (String) request.getAttribute("email");
        try {
            MySqlClientDao clientDao=new MySqlClientDao(MySqlDaoFactory.getConnection());
            Client client =clientDao.getClientByEmail(email);
            client.setAddress(request.getParameter("address"));
            client.setEmail(request.getParameter("email"));
            client.setFirstName(request.getParameter("firstName"));
            if(client.getPassword()!=null) {
                client.setPassword(EncodingPassword.hash(request.getParameter("password")));
            }
            client.setLastName(request.getParameter("lastName"));
            client.setPatronymic(request.getParameter("patronymic"));
            clientDao.update(client);
            clientDao.persist(client);
        } catch (SQLException e) {
            logger.error("SQLException",e);
        }
        request.getRequestDispatcher("/ServletForFirstPage").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        try {
            MySqlClientDao clientDao = new MySqlClientDao(MySqlDaoFactory.getConnection());
            Client client = clientDao.getClientByEmail(email);
            request.setAttribute("client",client);
        } catch (SQLException e) {
            logger.error("SQLException",e);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/client/Profile.jsp").forward(request,response);
    }
}
