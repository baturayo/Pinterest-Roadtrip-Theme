/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import roadtrip.entity.Checkpoint;
import roadtrip.session.CheckpointFacade;
import roadtrip.session.PhotoFacade;

/**
 *
 * @author Michael
 */
@WebServlet(name = "Checkpointservlet", urlPatterns = {"/checkpoint"})
public class Checkpointservlet extends HttpServlet {
    
    @EJB
    private CheckpointFacade checkpointFacade;
    
    
    @EJB
    private PhotoFacade photoFacade;


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

        
        String url = "/WEB-INF/view/checkpoint.jsp";
        
        Checkpoint springfield = new Checkpoint();
        springfield.setId(1);
        springfield.setName("Springfield");
        springfield.setDescription("Simpsons Town");
        springfield.setX(1.0);
        springfield.setY(1.0);
        
        checkpointFacade.edit(springfield);
        
        Integer cid = Integer.parseInt(request.getParameter("id"));
        
        Checkpoint cp = checkpointFacade.find(cid);
        
        request.setAttribute("Checkpoint", cp);
        
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
