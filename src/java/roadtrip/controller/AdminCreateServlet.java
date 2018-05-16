/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import roadtrip.entity.Checkpoint;
import roadtrip.entity.Country;
import roadtrip.entity.Road;
import roadtrip.entity.RoadTripUser;
import roadtrip.session.CheckpointFacade;
import roadtrip.session.UserFacade;
import roadtrip.session.CountryFacade;
import roadtrip.session.RoadFacade;



/**
 *
 * @author Michael
 */
@WebServlet(name = "AdminCreateServlet", urlPatterns = {"/create"})
public class AdminCreateServlet extends HttpServlet {
    
    @EJB
    private CountryFacade countryFacade;
    
    @EJB
    private CheckpointFacade checkpointFacade;
    
    @EJB
    private RoadFacade roadFacade;
    
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
        
        String url = "/WEB-INF/view/admincreate.jsp";
        request.getRequestDispatcher(url).forward(request, response);


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
        
        if (request.getParameter("formName").equals("createcp1")){
            createCheckPoint(request, response);
        }
        else if (request.getParameter("formName").equals("creater1")){
            createRoad(request, response);
        }
        else if (request.getParameter("formName").equals("linkcpr1")){
            linkCheckPointRoad(request, response);
        }

        String url = "/WEB-INF/view/admincreate.jsp";
        request.getRequestDispatcher(url).forward(request, response);

    }
    
    public void createCheckPoint(HttpServletRequest request, HttpServletResponse response) {

        String cpname = request.getParameter("newcpname");
        String countrycode = request.getParameter("newcpcountry");
        String cpdesc = request.getParameter("newcpdesc");
        
        Double X;
        X = Double.parseDouble(request.getParameter("newcpx"));
        
        Double Y;
        Y = Double.parseDouble(request.getParameter("newcpy"));
        
        if(!checkpointFacade.checkUniqueCheckpointName(cpname)){
            request.setAttribute("creationError", "Checkpoint name already in use");
            return;
        }
        if(!checkpointFacade.checkUniqueCoordinates(X, Y)){
            request.setAttribute("creationError", "Coordinate pair already in use");
            return;
        }
        
        Integer countryid = countryFacade.getByCode(countrycode);
        Country country = countryFacade.find(countryid);
        
        Checkpoint cp = new Checkpoint();
        cp.setName(cpname);
        cp.setCountry(country);
        cp.setDescription(cpdesc);
        cp.setX(X);
        cp.setY(Y);
                
        checkpointFacade.create(cp);

        
    }
    public void createRoad(HttpServletRequest request, HttpServletResponse response) {
        
        String rname = request.getParameter("newroad");
        String countrycode = request.getParameter("newroadcountry");
        String rdesc = request.getParameter("newroaddesc");
        
        if(!roadFacade.checkUniqueRoadName(rname)){
            request.setAttribute("creationError", "Road name already in use");
            return;
        }
        
        Integer countryid = countryFacade.getByCode(countrycode);
        Country country = countryFacade.find(countryid);
        
        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        RoadTripUser user = userFacade.find(id);

        
        Road r = new Road();
        r.setCountry(country);
        r.setName(rname);
        r.setDescription(rdesc);
        r.setUser(user);
        
        roadFacade.create(r);
        
    }    
    public void linkCheckPointRoad(HttpServletRequest request, HttpServletResponse response) {
        
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
