/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
@WebServlet(name = "MessageServlet", urlPatterns = {"/messages"})
public class MessageServlet extends HttpServlet {
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
        
        // Show the list of usernames that loggedIn user messaged before
        Set<String> userNames = getMessagedUserNames(loggedInUser);
        request.setAttribute("userNames", userNames);
        
        String url = "/WEB-INF/view/message.jsp";
        
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
        String url = "/WEB-INF/view/message.jsp";
        
        HttpSession session = request.getSession();
        Integer loggedInUserId = (Integer) session.getAttribute("userId");
        User loggedInUser = userFacade.find(loggedInUserId);
        
        String receiverUserName = request.getParameter("receiverUserName");
        System.out.println("Receiver :"+receiverUserName);
        Integer receiverUserId = userFacade.getUserID(receiverUserName);
        User receiverUser = userFacade.find(receiverUserId);
        
        // Show the list of usernames that loggedIn user messaged before
        Set<String> userNames = getMessagedUserNames(loggedInUser);
        request.setAttribute("userNames", userNames);
        
        
        // Show messages
        List<Message> messages = getMessages(loggedInUser, receiverUser, request);
        request.setAttribute("messages", messages);
         
        
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
    
    private void sendMessage(User sender, User receiver, HttpServletRequest request){
            String message = "message";
            Message msg = new Message();
            msg.setMessage(message);
            msg.setSender(sender);
            msg.setReceiver(receiver);
            sender.getSent().add(msg);
            receiver.getReceived().add(msg);
            messageFacade.create(msg);
            userFacade.edit(sender);
            userFacade.edit(receiver);
    }
    
    private List<Message> getMessages(User loggedInUser, User receiverUser,  HttpServletRequest request){
            List<Integer> messageIds = messageFacade.getMessages(loggedInUser, receiverUser);
            List<Message> messages;
            messages = new ArrayList<>();
            messageIds.forEach((messageId) -> {
                Message message = messageFacade.find(messageId);
                messages.add(message);
            });
            return messages;
             
    }
    
    private Set<String> getMessagedUserNames(User loggedInUser){
        // Create a list that include all messages of the logged in user
        List<Message> sentMessages = loggedInUser.getSent();
        List<Message> receivedMessages = loggedInUser.getReceived();
        List<Message> allMessages;
        allMessages = new ArrayList<>(sentMessages);
        allMessages.addAll(receivedMessages);
        System.out.println(allMessages.size());
        
        // Create a set that user has been messaged
        Set<String> userNames;
        userNames = new HashSet<String>() {{
            allMessages.stream().map((message) -> message.getReceiver().getUsername())
                    .forEachOrdered((userName) -> {
                if (userName.equals(loggedInUser.getUsername())){
                    // Don't add loggedInUser to Message List
                }else{
                    add(userName);
                }              
            });
        }};
        return userNames;        
    }
    
    
}
