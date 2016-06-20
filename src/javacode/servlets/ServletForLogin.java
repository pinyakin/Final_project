package javacode.servlets;

import javacode.DAO.Connections;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * Created by ww on 26.05.2016.
 *
 *
 *
 * Servlet for login
 */
@WebServlet(name = "ServletForLogin",urlPatterns = "/Login")
public class ServletForLogin extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletForLogin.class);

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        if(email == null || password == null) {
            logger.error("Troubles with parameters");
            response.sendError(400);
            return;
        }

        if (email.trim().isEmpty() || password.trim().isEmpty()) {
            logger.debug("Some empty fields");
            request.setAttribute("messageSignIn", "empty");
            doGet(request, response);
            return;
        }

        try {
            final MySqlClientDao clientDao = (MySqlClientDao) Connections.getFactory().getClientDao(MySqlDaoFactory.getConnection());
            final Client clientByEmail = clientDao.getClientByEmail(email);

            if (clientByEmail == null) {
                logger.debug("No user with such email");
                request.setAttribute("messageSignIn","nosuchuser");
                doGet(request, response);
                return;

                }
            if (clientByEmail.getPassword().equals(EncodingPassword.hash(password))) {
                HttpSession session = request.getSession();
                session.setAttribute("user_session",clientByEmail);
                response.sendRedirect("/ServletForFirstPage");
                return;
            }
                logger.debug("Wrong password");
                request.setAttribute("messageSignIn", "notcorrectpassword");
                doGet(request, response);
            }
         catch (SQLException e) {
            logger.error("SQLException:",e);
        }
    }
    /**
     * Forward to login,jsp
     * @param request request
     * @param response response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     request.getRequestDispatcher("/WEB-INF/jsp/shared_jsp/page.jsp").forward(request,response);
    }
}
