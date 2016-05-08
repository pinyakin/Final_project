package shared;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ww on 02.05.2016.
 */
@WebServlet(name = "ServletForFirstPage", urlPatterns = "/ServletForFirstPage")
public class ServletForFirstPage extends HttpServlet {
    public ServletForFirstPage() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html");

        MySqlDaoFactory mySqlDaoFactory =new MySqlDaoFactory();
        MySqlRaceDAO mySqlRaceDAO;
        List<Race> list;
        try {
            mySqlDaoFactory.getRaceDao(mySqlDaoFactory.getConnection());
            mySqlRaceDAO =new MySqlRaceDAO(mySqlDaoFactory.getConnection());
            list= mySqlRaceDAO.getFutureRace();

            request.setAttribute("races",list);
            request.getRequestDispatcher("/index.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
