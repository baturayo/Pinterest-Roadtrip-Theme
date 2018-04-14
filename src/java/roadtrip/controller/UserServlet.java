/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
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
@WebServlet(name = "UserServlet", urlPatterns = {"/users/*"})
public class UserServlet extends HttpServlet {
    
    @EJB
    private UserFacade userFacade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);
        
        String URI = request.getRequestURL().toString();
        String userName = URI.substring(URI.lastIndexOf('/') + 1);
        
        if(session.getAttribute("userID") == userFacade.getUserID(userName)){
            getPrivateUserInfo(user, request);
        }
        else{
            getPublicUserInfo(userName, request);
        }
        
        
        String url = "/WEB-INF/view/userpage.jsp";
        
        
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
        
        String url = "/WEB-INF/view/userpage.jsp";       
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
    
    private void getPrivateUserInfo(User user, HttpServletRequest request){          
            String name = user.getFirstname();
            String lastName = user.getSecondname();
            Integer followerCount = user.getFollowee().size();
            
            System.out.print(followerCount);
            String name_surname = name + ' ' + lastName;
            request.setAttribute("name_surname", name_surname);     
    }
    
    private void getPublicUserInfo(String userName, HttpServletRequest request){
            Integer id = userFacade.getUserID(userName);
            User user = userFacade.find(id);
            String name = user.getFirstname();
            String lastName = user.getSecondname();
            Integer followerCount = user.getFollowee().size();
            
            System.out.print(followerCount);
            String name_surname = name + ' ' + lastName;
            request.setAttribute("name_surname", name_surname);     
    }
    
}
