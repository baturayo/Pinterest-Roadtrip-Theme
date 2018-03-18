/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import roadtrip.entity.User;
import roadtrip.session.LoggedInTimestampsFacade;
import roadtrip.session.UserFacade;

/**
 *
 * @author cekef
 */
@WebServlet(name = "MainServlet", urlPatterns = {"/main","/settings","/stats"})
public class MainServlet extends HttpServlet {
    
    @EJB
    LoggedInTimestampsFacade timestampsFacade;
    
    @EJB
    private UserFacade userFacade;
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(!LoginRedirect(request,response)){
            return;
        }
        String path = request.getServletPath();
        String url = "/WEB-INF/view/"+path+".jsp";
        if(path.equals("/stats")){
            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("userId");
            List<Timestamp> stamps = timestampsFacade.findTimestamps(id);
            List<JsonObject> stampsJson=stamps.stream()
                    .map(stamp -> {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(stamp);
                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("year",cal.get(Calendar.YEAR));
                        objectBuilder.add("month",cal.get(Calendar.MONTH));
                        objectBuilder.add("day", cal.get(Calendar.DAY_OF_MONTH));
                        return objectBuilder.build();
                    }
                    )
                    .collect(Collectors.toList());
            request.setAttribute("logins",stampsJson);
        }
        if(path.equals("/settings")){
            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("userId");
            User user = userFacade.find(id);
            
            request.setAttribute("settingsusername",user.getUsername());
            request.setAttribute("settingsemail",user.getEmail());
            request.setAttribute("settingsfirstname",user.getFirstname());
            request.setAttribute("settingslastname",user.getSecondname());
            request.setAttribute("settingscountry",user.getCountry());
            if( user.getGender() == 0){
                request.setAttribute("settingsgender","Male");   
            }
            else if (user.getGender() == 1) {
                request.setAttribute("settingsgender","Female");   
            }
            else if (user.getGender() == 2) {
                request.setAttribute("settingsgender","Other");   
            }
        }

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
        
        if(!LoginRedirect(request,response)){
            return;
        }
        String path = request.getServletPath();
    
        if(path.equals("/settings")){
            changeUserInfo(request, response);
        }
        String url = "/WEB-INF/view"+path+".jsp";

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
    
    private Boolean LoginRedirect(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        if (null == id) {
            request.setAttribute("loginError", "Please log in!");
            request.getRequestDispatcher("WEB-INF/login/login.jsp").forward(request, response);
            return false;
        }
        return true;
    }
    private void changeUserInfo(HttpServletRequest request,HttpServletResponse response){
        if (request.getParameter("formName").equals("changeemail1")){
                changeUserEmail(request, response);
        }
        else if (request.getParameter("formName").equals("changeusername1")){
                changeUserName(request, response);
        }
        else if (request.getParameter("formName").equals("changefirstname1")){
                changeUserFirstName(request, response);
        }
        else if (request.getParameter("formName").equals("changelastname1")){
                changeUserLastName(request, response);
        }
        else if (request.getParameter("formName").equals("changecountry1")){
                changeUserCountry(request, response);
        }
        else if (request.getParameter("formName").equals("changegender1")){
                changeUserGender(request, response);
        }
        else if (request.getParameter("formName").equals("changepassword1")){
                changeUserPassword(request, response);
        }  
    }
    private void changeUserEmail(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);
        String newemail = request.getParameter("newemail");
        if (userFacade.checkUniqueEmail(newemail)){
            user.setEmail(newemail);
            userFacade.edit(user);
        }
        else {
            request.setAttribute("verificationError", "E-mail already in use");
        }
    }
    private void changeUserName(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);
        String newusername = request.getParameter("newusername");
        if(userFacade.checkUniqueUsername(newusername)){
            user.setUsername(newusername);
            userFacade.edit(user);
        }
        else {
            request.setAttribute("verificationError", "Username already in use");
        }
    }

    private void changeUserPassword(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);
        
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        
        if(oldpassword.equals(userFacade.getCurrentUserPassword(id))){
            user.setPassword(newpassword);
            userFacade.edit(user);
        }
        else {
            request.setAttribute("verificationError", "Incorrect password");
        }
    }
    
    private void changeUserFirstName(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);
        String newfirstname = request.getParameter("newfirstname");
        user.setFirstname(newfirstname);

        userFacade.edit(user);     
    }
    
    private void changeUserLastName(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);
        String newlastname = request.getParameter("newlastname");
        user.setSecondname(newlastname);

        userFacade.edit(user);      
    }
    private void changeUserCountry(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);
        String newcountry = request.getParameter("newcountry");
        user.setCountry(newcountry);

        userFacade.edit(user);      
    }           
    private void changeUserGender(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);
        String newgender = request.getParameter("newgender");
              
        Integer genderDB = null;
        switch (newgender) {
          case "Male":
              genderDB = 0;
              break;
          case "Female":
              genderDB = 1;
              break;
          case "Other":
              genderDB = 2;
              break;
          default:
              break;
        }      
        user.setGender(genderDB);
        userFacade.edit(user);     
    }
}

