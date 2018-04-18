/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import java.util.Objects;
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
        Integer loggedInUserId = (Integer) session.getAttribute("userId");
        User loggedInUser = userFacade.find(loggedInUserId);
        
        String URI = request.getRequestURL().toString();
        String visitedUser_name = URI.substring(URI.lastIndexOf('/') + 1);
        Integer visitedUserId = userFacade.getUserID(visitedUser_name);
        User visitedUser = userFacade.find(visitedUserId);
        
        if(Objects.equals(loggedInUserId, visitedUserId)){
            getPrivateUserInfo(loggedInUser, request);
        }
        else{
            
            getPublicUserInfo(loggedInUser, visitedUser, request);
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
        HttpSession session = request.getSession();
        Integer loggedInUserId = (Integer) session.getAttribute("userId");
        User loggedInUser = userFacade.find(loggedInUserId);
        
        String URI = request.getRequestURL().toString();
        String visitedUser_name = URI.substring(URI.lastIndexOf('/') + 1);
        Integer visitedUserId = userFacade.getUserID(visitedUser_name);
        User visitedUser = userFacade.find(visitedUserId);
        
        
        if(Objects.equals(loggedInUserId, visitedUserId)){
            getPrivateUserInfo(loggedInUser, request);
        }
        else{
            getPublicUserInfo(loggedInUser, visitedUser, request);
        }
        
        String url = "/WEB-INF/view/userpage.jsp";  
        
        // Handles following
        if(Objects.equals(request.getParameter("follow"), "Follow!")){
            userFacade.followUser(loggedInUser, visitedUser);
        } 
        else if(Objects.equals(request.getParameter("follow"), "Unfollow!")){
            userFacade.unfollowUser(loggedInUser, visitedUser);
        }
        
        // Handles blocking
        if(Objects.equals(request.getParameter("block"), "Block!")){
            userFacade.blockUser(loggedInUser, visitedUser);
        } 
        else if(Objects.equals(request.getParameter("block"), "Unblock!")){
            userFacade.unblockUser(loggedInUser, visitedUser);
        }
        
        // Refresh pages if necessary
        if(request.getParameter("block") != null ||
           request.getParameter("follow") != null){
            response.sendRedirect(URI);
        }
        
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
    
    private void getPrivateUserInfo(User loggedInUser, HttpServletRequest request){          
            String name = loggedInUser.getFirstname();
            String lastName = loggedInUser.getSecondname();
            Integer followeeCount = loggedInUser.getFollowee().size();
            Integer followerCount = loggedInUser.getFollower().size();
            
            String name_surname = name + ' ' + lastName;
            request.setAttribute("name_surname", name_surname);
            request.setAttribute("followee", followeeCount);
            request.setAttribute("follower", followerCount);
            request.setAttribute("canFollow", -1); 
            request.setAttribute("isBlocked", 0);
            request.setAttribute("canBlock", -1);
              
    }
    
    private void getPublicUserInfo(User loggedInUser, User visitedUser, HttpServletRequest request){
            String name = visitedUser.getFirstname();
            String lastName = visitedUser.getSecondname();
            Integer followeeCount = visitedUser.getFollowee().size();
            Integer followerCount = visitedUser.getFollower().size();
            
            String name_surname = name + ' ' + lastName;
            request.setAttribute("name_surname", name_surname);
            request.setAttribute("followee", followeeCount);
            request.setAttribute("follower", followerCount);            
            
            // Handles Following
            if (userFacade.checkFollowUser(loggedInUser, visitedUser)){
                request.setAttribute("canFollow", 0); //If visited user has already followed
            } else{
                request.setAttribute("canFollow", 1); //If visited user is not followed yet
            }
            
            // Handles Blocking User
            if (userFacade.checkBlockedUser(loggedInUser, visitedUser)){              
                request.setAttribute("isBlocked", 1); //If visited user blocs you
            } else{
                request.setAttribute("isBlocked", 0); //If visited user does not block you
            }
            
            // Handles Unblocing User
            if (userFacade.checkBlockedUser(visitedUser, loggedInUser)){  
                request.setAttribute("canBlock", 0); //If visited user already blocked
            } else{
                request.setAttribute("canBlock", 1); //If visited user is not blocked
            }     
    }
    
}
