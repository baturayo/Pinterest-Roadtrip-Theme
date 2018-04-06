/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Michael
 */
@WebServlet(name = "Checkpointservlet", urlPatterns = {"/checkpoint?*"})
public class Checkpointservlet extends HttpServlet {

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

        
        String URI = request.getRequestURL().toString();
        System.out.println(URI);
        
        String Contextpath = request.getContextPath();
        System.out.println(Contextpath);
        
        String Servletpath = request.getServletPath();
        System.out.println(Servletpath);
        
        String Pathinfo = request.getPathInfo();
        System.out.println(Pathinfo);
        
        String Querystring = request.getQueryString();
        System.out.println(Querystring);
        
        request.setAttribute("uri", URI);
        request.setAttribute("context", Contextpath);
        request.setAttribute("servlet", Servletpath);
        request.setAttribute("path", Pathinfo);
        request.setAttribute("query", Querystring);


        String url = "/WEB-INF/view/checkpoint.jsp";


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
        
        String url = "/WEB-INF/view/checkpoint.jsp";

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

}
