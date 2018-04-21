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
        
        //TODO: SET THE USERID because the above gave an error
        User loggedInUser = userFacade.find(1);
        
        String receiverUserName = request.getParameter("formName");
        Integer receiverUserId = userFacade.getUserID(receiverUserName);
        
        //TODO: SET THE USERID because the above gave an error
        User receiverUser = userFacade.find(2);
        
        if(request.getParameter("formName").equals("bora")){
           sendMessage(loggedInUser, receiverUser, request);
           List<Message> messages = getMessages(loggedInUser, receiverUser, request);
           request.setAttribute("messages", messages);
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
    
    private void sendMessage(User sender, User receiver, HttpServletRequest request){
            String message = "message";
//            System.out.println(loggedInUserId);
//            System.out.println(receiverUserId);

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
    
    
}
