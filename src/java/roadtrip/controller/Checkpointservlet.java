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
import javax.servlet.http.HttpSession;
import roadtrip.entity.Checkpoint;
import roadtrip.entity.Photo;
import roadtrip.entity.User;
import roadtrip.session.CheckpointFacade;
import roadtrip.session.PhotoFacade;
import roadtrip.session.UserFacade;

/**
 *
 * @author Michael
 */
@WebServlet(name = "Checkpointservlet", urlPatterns = {"/checkpoint","/visited","/wanttovisit"})
public class Checkpointservlet extends HttpServlet {
    
    @EJB
    private CheckpointFacade checkpointFacade;
    
    @EJB
    private PhotoFacade photoFacade;

    @EJB
    private UserFacade userFacade;


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

        String path = request.getServletPath();
        if (path.equals("/checkpoint")) {

            Checkpoint springfield = new Checkpoint();
            springfield.setName("Springfield");
            springfield.setDescription("Simpsons Town");
            springfield.setX(1.0);
            springfield.setY(1.0);

            Photo photo1 = new Photo();
            photo1.setDescription("Krusty Burger");
            photo1.setUrl("http://adn.blam.be/springfield/krustyburger.jpg");
            photo1.setCheckpoint(springfield);

            Photo photo2 = new Photo();
            photo2.setDescription("Springfield Arms");
            photo2.setUrl("https://vignette.wikia.nocookie.net/simpsons/images/3/39/Springfield_arms.png/revision/latest?cb=20100923185231");
            photo2.setCheckpoint(springfield);

            Photo photo3 = new Photo();
            photo3.setDescription("Kwik-E-Mart");
            photo3.setUrl("https://vignette.wikia.nocookie.net/simpsons/images/2/2e/Kwikemart.jpg/revision/latest?cb=20061223160324");
            photo3.setCheckpoint(springfield);

            List<Photo> fotos = new ArrayList<>();

            fotos.add(photo1);
            fotos.add(photo2);
            fotos.add(photo3);

            springfield.setPhotos(fotos);

            checkpointFacade.edit(springfield);

            Integer cid = Integer.parseInt(request.getParameter("id"));
            Checkpoint cp = checkpointFacade.find(cid);

            List<Photo> photos = cp.getPhotos();
            photos.isEmpty();

            request.setAttribute("Checkpoint", cp);
            request.setAttribute("photos", photos);

        }
        else if (path.equals("/visited")) {
            url = "/WEB-INF/view/visited.jsp";
            
            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("userId");
            User user = userFacade.find(id);
            List<Checkpoint> visited = user.getVisited();
            visited.isEmpty();
            
            request.setAttribute("user", user);
            request.setAttribute("visited", visited);

        }
        else if (path.equals("/wanttovisit")) {
            url = "/WEB-INF/view/wanttovisit.jsp";
            
            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("userId");
            User user = userFacade.find(id);
            List<Checkpoint> wanttovisit = user.getWanttovisit();
            wanttovisit.isEmpty();
            
            request.setAttribute("user", user);
            request.setAttribute("wanttovisit", wanttovisit);
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
        
        String url = "/WEB-INF/view/checkpoint.jsp";

        HttpSession session = request.getSession();
        Integer id = (Integer) session.getAttribute("userId");
        User user = userFacade.find(id);

        Integer cid = Integer.parseInt(request.getParameter("id"));   
        Checkpoint cp = checkpointFacade.find(cid);
        
        if (request.getParameter("formName").equals("setvisited")){
            
            List<Checkpoint> visited = user.getVisited();
            visited.isEmpty();
            if(!visited.contains(cp)) {
                visited.add(cp);
            }
            user.setVisited(visited);
            userFacade.edit(user);

        }
        else if (request.getParameter("formName").equals("setwanttovisit")){
            List<Checkpoint> wanttovisit = user.getWanttovisit();
            wanttovisit.isEmpty();
            if(!wanttovisit.contains(cp)) {
                wanttovisit.add(cp);
            }
            user.setWanttovisit(wanttovisit);
            userFacade.edit(user);
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

}
