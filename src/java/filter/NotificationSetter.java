/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import roadtrip.entity.User;
import roadtrip.session.UserFacade;

/**
 *
 * @author cekef
 */
@WebFilter(filterName = "NotificationSetter", urlPatterns = {"/*"})
public class NotificationSetter implements Filter {
    
    @EJB
    private UserFacade userFacade;
    
    public NotificationSetter() {
    }    

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpSession session = hrequest.getSession();    
        Integer id = (Integer) session.getAttribute("userId");
        if (id != null){
            User thisUser = userFacade.find(id);
            List notifications = thisUser.getNotifications();
            notifications.isEmpty();
            System.out.println(notifications.size());
            request.setAttribute("notifications", notifications.size());
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

}
