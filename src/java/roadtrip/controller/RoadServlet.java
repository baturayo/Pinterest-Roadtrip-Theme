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
import roadtrip.entity.Checkpoint;
import roadtrip.entity.Country;
import roadtrip.entity.Photo;
import roadtrip.entity.Road;
import roadtrip.session.CheckpointFacade;
import roadtrip.session.CountryFacade;
import roadtrip.session.RoadFacade;

/**
 *
 * @author cekef
 */
@WebServlet(name = "RoadServlet", urlPatterns = {"/road/*"})
public class RoadServlet extends HttpServlet {

    @EJB
    private RoadFacade roadFacade;

    @EJB
    private CheckpointFacade checkpointFacade;
    
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

        //Generate road for testing purposes
        // TODO: Delete this road
        Road testRoad = new Road();
        testRoad.setName("Cartoon Road");
        testRoad.setDescription("Visit all of your favorite cartoon towns and cities.");

        Checkpoint southpark = new Checkpoint();
        southpark.setName("South Park");
        southpark.setDescription("Quiet town in Colorado");
        southpark.setX(1.0);
        southpark.setY(1.0);

        Photo photo1 = new Photo();
        photo1.setDescription("Town");
        photo1.setUrl("http://adn.blam.be/springfield/krustyburger.jpghttp://urbansome.com/wp-content/uploads/2016/04/south_park-1000x600.jpeg");
        photo1.setCheckpoint(southpark);

        Photo photo2 = new Photo();
        photo2.setDescription("Bus stop");
        photo2.setUrl("https://vignette.wikia.nocookie.net/southpark/images/c/c8/Busstop.jpg");
        photo2.setCheckpoint(southpark);

        Photo photo3 = new Photo();
        photo3.setDescription("South Park Elementary");
        photo3.setUrl("https://vignette.wikia.nocookie.net/southpark/images/9/92/School_6.png");
        photo3.setCheckpoint(southpark);

        List<Photo> fotos = new ArrayList<>();

        fotos.add(photo1);
        fotos.add(photo2);
        fotos.add(photo3);

        southpark.setPhotos(fotos);
        
        // Was Springfield in my case, put it here so it can be the same as the one above
        Checkpoint springfield = checkpointFacade.find(1);
        
        List<Checkpoint> tempcheckpoints = new ArrayList<>();
        tempcheckpoints.add(springfield);
        tempcheckpoints.add(southpark);
        testRoad.setCheckpoints(tempcheckpoints);
        
        Integer id = countryFacade.getByCode("US");
        
        Country country = countryFacade.find(id);
        southpark.setCountry(country);
        testRoad.setCountry(country);
        roadFacade.edit(testRoad);
        
        // END of TO BE DELETED

        String pathInfo = request.getPathInfo();
        String[] parameters = pathInfo.split("/");
        if (parameters.length != 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            System.out.println(parameters[1]);
            String number = parameters[1];
            System.out.println(number);
            int Lookup = Integer.parseInt(number);
            System.out.println(Lookup);
            Road road = roadFacade.find(Lookup);
            if (road == null) {
                response.sendRedirect("/RoadTrip/unknown.html");
            } else {
                String url = "/WEB-INF/view/road.jsp";
                List<Checkpoint> checkpoints = road.getCheckpoints();
                checkpoints.isEmpty(); // Force load checkpoints
                request.setAttribute("road", road);
                request.setAttribute("checkpoints", checkpoints);
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
