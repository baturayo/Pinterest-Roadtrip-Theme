/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import roadtrip.entity.Checkpoint;
import roadtrip.entity.Photo;
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
        springfield.setName("Springfield");
        springfield.setDescription("Simpsons Town");
        springfield.setX(1.0);
        springfield.setY(1.0);
        
        Photo photo1 = new Photo();
        photo1.setDescription("Krusty Burger");
        photo1.setUrl("http://adn.blam.be/springfield/krustyburger.jpg");
        photo1.setCheckpoint(springfield);
        
        //photoFacade.edit(photo1);
        
        Photo photo2 = new Photo();
        photo2.setDescription("Springfield Arms");
        photo2.setUrl("https://vignette.wikia.nocookie.net/simpsons/images/3/39/Springfield_arms.png/revision/latest?cb=20100923185231");
        photo2.setCheckpoint(springfield);
        
        //photoFacade.edit(photo2);

        
        Photo photo3 = new Photo();
        photo3.setDescription("Kwik-E-Mart");
        photo3.setUrl("https://vignette.wikia.nocookie.net/simpsons/images/2/2e/Kwikemart.jpg/revision/latest?cb=20061223160324");
        photo3.setCheckpoint(springfield);
        
        //photoFacade.edit(photo3);
        
        List<Photo> fotos = new ArrayList<Photo>();
        
        fotos.add(photo1);
        fotos.add(photo2);
        fotos.add(photo3);


        springfield.setPhotos(fotos);
  
        checkpointFacade.edit(springfield);

        Integer cid = Integer.parseInt(request.getParameter("id"));   
        Checkpoint cp = checkpointFacade.find(cid);
        
        List<Photo> photos = cp.getPhotos();
        photos.isEmpty();
        
        /*ArrayList<Photo> lolphotos = new ArrayList();
        for(Photo foto : photos){
            lolphotos.add(foto);
        }*/
        request.setAttribute("Checkpoint", cp);
        request.setAttribute("fotos", photos);
        


        
        Checkpoint temp = checkpointFacade.find(1);
        
        System.out.println(temp.getPhotos().size());
        System.out.println(photos);
        
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
