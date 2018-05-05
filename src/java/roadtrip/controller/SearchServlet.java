/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import roadtrip.entity.User;
import roadtrip.session.UserFacade;

/**
 *
 * @author baturay
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    
    @EJB
    private UserFacade userFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String url = "/WEB-INF/view/search.jsp";
        List<User> searchList = (List<User>) session.getAttribute("searchList");
        request.setAttribute("searchList", searchList);
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        String url = "/WEB-INF/view/search.jsp";
     
        String query = request.getParameter("search");
        List<User> users = new ArrayList<User>();
        users = searchUserInfo(request, query, users);
        request.setAttribute("searchList", users);
       
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    private List<User> searchUserInfo(HttpServletRequest request, String query, List<User> users){           
            Integer userID;
            userID = userFacade.getUserID(query); // Query is the userName in this case
            User user = userFacade.find(userID);
            users.add(user);    
            return users;
    }
}
