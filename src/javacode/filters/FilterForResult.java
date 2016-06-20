package javacode.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ww on 05.06.2016.
 */
@WebFilter(filterName = "FilterForResult")
public class FilterForResult implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest request = (HttpServletRequest) req;
//        if(request.getSession().getAttribute("user_session")!=null) {
//            Client user = (Client) request.getSession().getAttribute("user_session");
//            String email = user.getEmail();
//            request.setAttribute("userName", email);
//        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
