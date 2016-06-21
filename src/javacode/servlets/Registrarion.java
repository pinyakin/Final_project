package javacode.servlets;

import javacode.DAO.MySqlClientDao;
import javacode.DAO.MySqlDaoFactory;
import javacode.entity.Client;
import javacode.servlets.security.EncodingPassword;
import org.apache.log4j.Logger;

import javax.servlet.Registration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Timestamp;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ww on 21.05.2016.
 */
@WebServlet(name = "Registration",urlPatterns = "/Registration")
public class Registrarion extends HttpServlet {

    private final Logger logger = Logger.getLogger(Registration.class);
    private final Pattern p = Pattern.compile("[0-9a-z_]+@[0-9a-z_^\\.]+\\.[a-z]{2,3}");
    private Matcher m;

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("lastName") == null || request.getParameter("firstName") == null ||
                request.getParameter("fatherName") == null || request.getParameter("date") == null ||
                request.getParameter("inputEmail") == null || request.getParameter("inputPassword") == null||
                request.getParameter("phoneNumber")==null  || request.getParameter("postalAddress")==null) {
            logger.error("Troubles with parameters");
            response.sendError(400);
            return;
        }

        try {
            MySqlClientDao clientDao=new MySqlClientDao(MySqlDaoFactory.getConnection());
            if(clientDao.getClientByEmail(request.getParameter("inputEmail"))!=null){
                logger.debug("Wrong such email is already exists");
                request.setAttribute("messageRegistration","emailExists");
                doGet(request, response);
                return;
            }
        } catch (SQLException e) {
            logger.debug(e);
        }

        Client client=new Client();
        final String lastName=request.getParameter("lastName");
        final String firstName=request.getParameter("firstName");
        final String fatherName=request.getParameter("fatherName");
        final String email =request.getParameter("inputEmail");
        final String password=request.getParameter("inputPassword");
        final String tel=request.getParameter("phoneNumber");
        final String address=request.getParameter("postalAddress");

        if(lastName.trim().isEmpty()||firstName.trim().isEmpty()||fatherName.trim().isEmpty()||
                email.trim().isEmpty()||password.trim().isEmpty()||tel.trim().isEmpty()||
                request.getParameter("date").trim().isEmpty()||address.trim().isEmpty()){
            logger.debug("Some empty fields");
            request.setAttribute("messageRegistration","empty");
            doGet(request, response);
            return;
        }
        final Date date= Date.valueOf(request.getParameter("date"));

        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int year=calendar.get(Calendar.YEAR);
        if(getCurrentYear()-year<18||year>getCurrentYear())
        {
            logger.debug("Incorrect birth date");
            request.setAttribute("messageRegistration","wrongDate");
            doGet(request, response);
            return;
        }
        if(lastName.length()>100 || firstName.length()>100 || fatherName.length()>100 ||
                email.length()>100 ) {
            logger.debug("Too long value");
            request.setAttribute("messageRegistration","tooLong");
            doGet(request, response);
            return;
        }

        m=p.matcher(email);
        if(!m.matches()){
            logger.debug("Wrong email format");
            request.setAttribute("messageRegistration","wrongEmail");
            doGet(request, response);
            return;
        }

        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setPatronymic(fatherName);
        client.setPassword(EncodingPassword.hash(password));
        client.setAddress(address);
        client.setEmail(email);
        client.setTel(tel);
        client.setDate(date);
        client.setBalance(0.00);

        try {
            MySqlClientDao clientDao=new MySqlClientDao(MySqlDaoFactory.getConnection());
            clientDao.persist(client);
        } catch (SQLException e) {
            logger.error("Trouble with insertion new Client");
            response.sendError(400);
            logger.debug(e);
        }
        response.sendRedirect("/ServletForFirstPage");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/shared_jsp/registration.jsp").forward(request,response);
    }

    private static int getCurrentYear() {
        java.util.Calendar calendar = java.util.Calendar.getInstance(java.util.TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new java.util.Date());
        return calendar.get(java.util.Calendar.YEAR);
    }
}
