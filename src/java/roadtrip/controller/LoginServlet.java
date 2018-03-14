/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import roadtrip.session.Register;
import roadtrip.session.LoginBean;

/**
 *
 * @author cekef
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/validate"})
public class LoginServlet extends HttpServlet {
    
    @EJB
    private Register register;
    
    @EJB
    private LoginBean loginBean;
    

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
        String userPath = request.getServletPath();
                
        System.out.println(userPath);
        
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/login/login.jsp";
        
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
        if (userPath.equals("/validate")){
            
            if(request.getParameter("formName").equals("LoginForm")){
                String email = request.getParameter("username");
                String password = request.getParameter("password");
                Integer id = loginBean.Login(email, password);
                System.err.println(id);
            } else if (!request.getParameter("formName").equals("RegisterForm")){
                // Receive username and password from login form
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String country = request.getParameter("country");
                String gender = request.getParameter("gender");
                String username = request.getParameter("username");
                String email = request.getParameter("email1");
                String password = request.getParameter("password1");

                Integer genderDB = null;
                switch (gender) {
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
                System.out.println("username: " + firstName);
                System.out.println("username: " + lastName);
                System.out.println("username: " + country);
                System.out.println("username: " + genderDB);
                System.out.println("username: " + username);
                System.out.println("username: " + email);
                System.out.println("username: " + password);

                register.placeOrder(firstName, lastName, country, genderDB, username, email, password);
            }
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/login" + userPath + ".jsp";
       
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
        return "Servlet handling logins";
    }// </editor-fold>

}
