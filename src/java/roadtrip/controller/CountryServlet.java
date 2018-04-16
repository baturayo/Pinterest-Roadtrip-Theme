/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import roadtrip.entity.Checkpoint;
import roadtrip.entity.Country;
import roadtrip.entity.Road;
import roadtrip.session.CountryFacade;

/**
 *
 * @author cekef
 */
@WebServlet(name = "CountryServlet", urlPatterns = {"/countries/*"})
public class CountryServlet extends HttpServlet {

    @EJB
    private CountryFacade countryFacade;
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] parameters = pathInfo.split("/");
        if (parameters.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            String url = "/WEB-INF/view/map.jsp";
            Integer countryId = countryFacade.getByCode(parameters[1]);
            if (countryId == null){
                response.sendRedirect("/RoadTrip/unknown.html");
            }
            else{
                Country country = countryFacade.find(countryId);
                List<Checkpoint> checkpoints = country.getCheckpoints();
                checkpoints.isEmpty();
                List<Road> roads = country.getRoads();
                roads.isEmpty();
                request.setAttribute("country", country);
                request.setAttribute("checkpoints", checkpoints);
                request.setAttribute("roads", roads);
                request.getRequestDispatcher(url).forward(request, response);
            }
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
