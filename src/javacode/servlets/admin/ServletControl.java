package javacode.servlets.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javacode.DAO.*;
import javacode.entity.Client;
import javacode.entity.Horse;
import javacode.entity.Race;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ww on 30.05.2016.
 *
 *
 * Servlet for show admin control panel with his resources
 */
@WebServlet(name = "ServletControl",urlPatterns = "/adminPanel")
public class ServletControl extends HttpServlet {
    private final Logger logger = Logger.getLogger(ServletControl.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i=1;
        if(request.getParameter("i")!=null)
            i= Integer.parseInt(request.getParameter("i"));
        MySqlDaoFactory mySqlDaoFactory =new MySqlDaoFactory();
        MySqlClientDao mySqlClientDao;
        MySqlRaceDAO mySqlRaceDAO;
        MySqlHorseDao mySqlHorseDao;
        ArrayList list=null;

        try {
            //  mySqlDaoFactory.getRaceDao(mySqlDaoFactory.getConnection());
            if(i==1) {
                list=new ArrayList<Client>();
                mySqlClientDao = new MySqlClientDao(mySqlDaoFactory.getConnection());
                list.remove(request.getSession().getAttribute("user_session"));
                list = (ArrayList) mySqlClientDao.getAll();
            }
            if(i==2) {
                list=new ArrayList<Horse>();
                mySqlHorseDao = new MySqlHorseDao(mySqlDaoFactory.getConnection());
                list = (ArrayList) mySqlHorseDao.getAll();
            }
            if(i==3) {
                list=new ArrayList<Race>();
                mySqlRaceDAO = new MySqlRaceDAO(mySqlDaoFactory.getConnection());
                list = (ArrayList) mySqlRaceDAO.getAll();
            }



            // ДОБАВИТЬ ЕРОР ПРИ ДРУГОМ И

            request.setAttribute("i",i);
            request.setAttribute("entity",list);
            request.getRequestDispatcher("/WEB-INF/jsp/admin_jsp/admin_panel.jsp").forward(request,response);

        } catch (SQLException e) {
            logger.error("SQLException:",e);
        }
    }
}
