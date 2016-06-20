package javacode.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ww on 26.05.2016.
 */
@WebServlet(name = "ServletDoLocale",urlPatterns = "/changeLocale")
public class ServletDoLocale extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String locale=request.getParameter("locale");
        request.getSession().setAttribute("javax.servlet.jsp.jstl.fmt.locale.session",locale);
        request.getRequestDispatcher("/ServletForFirstPage").forward(request,response);
    }

}
