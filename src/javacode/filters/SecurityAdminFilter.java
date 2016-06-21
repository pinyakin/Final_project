package javacode.filters;

import javacode.entity.Client;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ww on 21.06.2016.
 */
@WebFilter(filterName = "SecurityAdminFilter",urlPatterns = {"/addEvent","/addHorse","/addpart","/adminPanel","/eventcontrol",
                            "/horsecontrol","/profControle","/delete_client","/deleteEvent","/deletePart"})
public class SecurityAdminFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Client client = (Client) request.getSession().getAttribute("user_session");
        if ( client==null || client.getRole()==null || !client.getRole().equals("admin")) {
            request.getRequestDispatcher("/ServletForFirstPage").forward(request, response);
            return;
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
