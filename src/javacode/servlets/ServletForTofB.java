package javacode.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ww on 27.05.2016.
 *
 *
 * Servlet for forward to jsp about type of bets.
 */
@WebServlet(name = "ServletForTofB",urlPatterns = "/types_bets")
public class ServletForTofB extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/shared_jsp/type_bets.jsp").forward(request,response);
    }
}
