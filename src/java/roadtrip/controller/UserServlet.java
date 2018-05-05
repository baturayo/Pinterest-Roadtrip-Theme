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
import roadtrip.entity.Message;
import roadtrip.entity.User;
import roadtrip.session.MessageFacade;
import roadtrip.session.UserFacade;

/**
 *
 * @author baturay
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/users/*"})
public class UserServlet extends HttpServlet {
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private MessageFacade messageFacade;
    
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
        
        
        String url = "/WEB-INF/view/profilepage.jsp";
        
        
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
        
        String url = "/WEB-INF/view/profilepage.jsp";  
        
        // Handles following
        if(Objects.equals(request.getParameter("follow"), "Follow")){
            userFacade.followUser(loggedInUser, visitedUser);
        } 
        else if(Objects.equals(request.getParameter("follow"), "Unfollow")){
            userFacade.unfollowUser(loggedInUser, visitedUser);
        }
        
        // Handles blocking
        if(Objects.equals(request.getParameter("block"), "Block")){
            userFacade.blockUser(loggedInUser, visitedUser);
        } 
        else if(Objects.equals(request.getParameter("block"), "Unblock")){
            userFacade.unblockUser(loggedInUser, visitedUser);
        }
        
        // Handles Messaging User
        if(Objects.equals(request.getParameter("sendMessage"), "Message")){
            // Create an empty message to be able to start a conversation
            // Creating an empty message will also add visited user into messaged people list
            sendMessage("", loggedInUser, visitedUser, request);
            response.sendRedirect("/RoadTrip/messages");
        } 
        
        // Handles Showing Followers
        if(Objects.equals(request.getParameter("showFollowers"), "showFollowers")){
            session.setAttribute("searchList", visitedUser.getFollower());
            response.sendRedirect("/RoadTrip/search");
        } 
        
        // Handles Showing Followees
        if(Objects.equals(request.getParameter("showFollowees"), "showFollowees")){
            session.setAttribute("searchList", visitedUser.getFollowee());
            response.sendRedirect("/RoadTrip/search");
        } 
        
        // Handles Making a regular user to an admin
        if(Objects.equals(request.getParameter("Make Admin"), "Make Admin")){
            userFacade.makeAdmin(visitedUser, true);
        } 
        
        // Handles Deleting an Admin and make him/her a regular user
        if(Objects.equals(request.getParameter("Make Admin"), "Delete Admin")){
            userFacade.makeAdmin(visitedUser, false);
        } 
            
        // Refresh pages if necessary
        if(request.getParameter("block") != null ||
           request.getParameter("follow") != null ||
           request.getParameter("Make Admin") != null){
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
            name = name.substring(0, 1).toUpperCase() + name.substring(1); // Make first letter capital letter
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1); // Make first letter capital letter
            String name_surname = name + ' ' + lastName;
            
            Integer followeeCount = loggedInUser.getFollowee().size();
            Integer followerCount = loggedInUser.getFollower().size();
            
            String country = loggedInUser.getCountry();
            country = country.substring(0, 1).toUpperCase() + country.substring(1); // Make first letter capital letter
            
            request.setAttribute("name_surname", name_surname);
            request.setAttribute("followee", followeeCount);
            request.setAttribute("follower", followerCount);
            request.setAttribute("country", country);
            request.setAttribute("canFollow", -1); 
            request.setAttribute("isAdmin", 0); 
            request.setAttribute("isBlocked", 0);
            request.setAttribute("canBlock", -1);
              
    }
    
    private void getPublicUserInfo(User loggedInUser, User visitedUser, HttpServletRequest request){
            String name = visitedUser.getFirstname();
            String lastName = visitedUser.getSecondname();
            name = name.substring(0, 1).toUpperCase() + name.substring(1); // Make first letter capital letter
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1); // Make first letter capital letter
   
            String name_surname = name + ' ' + lastName;
            
            Integer followeeCount = visitedUser.getFollowee().size();
            Integer followerCount = visitedUser.getFollower().size();
            
            Integer isAdmin = loggedInUser.getIsAdmin() ? 1 : 0;
            String country = loggedInUser.getCountry();
            country = country.substring(0, 1).toUpperCase() + country.substring(1); // Make first letter capital letter
            
            request.setAttribute("name_surname", name_surname);
            request.setAttribute("followee", followeeCount);
            request.setAttribute("follower", followerCount);            
            request.setAttribute("country", country); 
            request.setAttribute("isAdmin", isAdmin);
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
            
            // Handles Making a User Admin or Deleting an Admin
            if (visitedUser.getIsAdmin() && loggedInUser.getIsAdmin()){  
                request.setAttribute("canMakeAdmin", 0); //If visited user and loggedin user are admins
            } else{
                request.setAttribute("canMakeAdmin", 1); //If visited user is not an admin
            }
            
            
    }
    private void sendMessage(String txt_message, User sender, User receiver, HttpServletRequest request){
        Message msg = new Message();
        msg.setMessage(txt_message);
        msg.setSender(sender);
        msg.setReceiver(receiver);
        sender.getSent().add(msg);
        receiver.getReceived().add(msg);
        messageFacade.create(msg);
        userFacade.edit(sender);
        userFacade.edit(receiver);
    }
    
}
