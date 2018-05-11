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
import javax.servlet.http.HttpSession;
import roadtrip.entity.Checkpoint;
import roadtrip.entity.Comment;
import roadtrip.entity.Notification;
import roadtrip.entity.Photo;
import roadtrip.entity.Road;
import roadtrip.entity.RoadTripUser;
import roadtrip.session.CheckpointFacade;
import roadtrip.session.NotificationFacade;
import roadtrip.session.PhotoFacade;
import roadtrip.session.UserFacade;

/**
 *
 * @author Michael
 */
@WebServlet(name = "Checkpointservlet", urlPatterns = {"/checkpoint","/visited","/followcp"})
public class Checkpointservlet extends HttpServlet {
    
    @EJB
    private CheckpointFacade checkpointFacade;
    
    @EJB
    private PhotoFacade photoFacade;

    @EJB
    private UserFacade userFacade;
    
    @EJB
    private NotificationFacade notificationFacade;

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

            handleCheckpointGET(request);


        }
        else if (path.equals("/visited")) {
            url = "/WEB-INF/view/visited.jsp";
            
            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("userId");
            RoadTripUser user = userFacade.find(id);
            List<Checkpoint> visited = user.getVisited();
            visited.isEmpty();
                        
            request.setAttribute("user", user);
            request.setAttribute("visited", visited);

        }
        else if (path.equals("/followcp")) {
            url = "/WEB-INF/view/followcp.jsp";
            
            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("userId");
            RoadTripUser user = userFacade.find(id);
            List<Checkpoint> followcp = user.getFollowCp();
            followcp.isEmpty();
            
            request.setAttribute("user", user);
            request.setAttribute("followcp", followcp);
        }
        
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleCheckpointGET(HttpServletRequest request) throws NumberFormatException {
//        Checkpoint springfield = new Checkpoint();
//        springfield.setName("Springfield");
//        springfield.setDescription("Simpsons Town");
//        springfield.setX(1.0);
//        springfield.setY(1.0);
//        
//        checkpointFacade.edit(springfield);
        
        Integer cid = Integer.parseInt(request.getParameter("id"));
        Checkpoint cp = checkpointFacade.find(cid);
        
        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userId");
        RoadTripUser user = userFacade.find(userid);
        
        List<Photo> photos = cp.getPhotos();
        photos.isEmpty();
        
        List<Road> roads = cp.getRoads();
        roads.isEmpty();
        
        request.setAttribute("Checkpoint", cp);
        request.setAttribute("photos", photos);
        request.setAttribute("roads", roads);
        
        if (user.getFollowCp().contains(cp)) {
            request.setAttribute("canFollow", 0);
        } else {
            request.setAttribute("canFollow", 1);
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
        
        String URI = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            URI  += "?" + request.getQueryString();
        }


        
        Boolean refresh = false;
        
        String path = request.getServletPath();
        if (path.equals("/checkpoint")) {

            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("userId");
            RoadTripUser user = userFacade.find(id);

            Integer cid = Integer.parseInt(request.getParameter("id"));
            Checkpoint cp = checkpointFacade.find(cid);
            
            String cpformvalue = request.getParameter("cpform");
            String photoformvalue = request.getParameter("photoform");
            String commentformvalue = request.getParameter("commentform");



            if(cpformvalue == null){
            }

            else if (cpformvalue.equals("setvisited")) {
                Notification notification = new Notification();
                notification.setText("you visited a checkpoint");
                notification.setUser(user);
                notificationFacade.create(notification);
                
                user.getNotifications().add(notification);
                userFacade.edit(user);

                handleVisitedButton(user, cp);
            }
            else if (cpformvalue.equals("setfollowcp")) {
                handleFollowButton(user, cp);
                refresh = true;
            }
            else if (cpformvalue.equals("setunfollowcp")) {
                handleUnFollowButton(user, cp);
                refresh = true;
            }
            else if(cpformvalue.equals("addphoto1")){
                
                handleAddPhoto(request, user, cp);
            }
            
            if (photoformvalue == null) {
            }
            else if(photoformvalue.startsWith("deletephoto")) {
                String s = request.getParameter("photoform");
                Integer photoid = Integer.parseInt((s.substring(s.lastIndexOf('o') + 1)));
                Photo tobedeleted = photoFacade.find(photoid);
                List<Photo> cpphotos = cp.getPhotos();
                cpphotos.remove(tobedeleted);
                cp.setPhotos(cpphotos);
                checkpointFacade.edit(cp);
                
            }
            else if(photoformvalue.startsWith("hiddenupdatephoto")) {
                String s = request.getParameter("photoform");
                Integer photoid = Integer.parseInt((s.substring(s.lastIndexOf('o') + 1)));
                Photo tobeupdated = photoFacade.find(photoid);
                List<Photo> cpphotos = cp.getPhotos();
                
                String updateddescription = request.getParameter("updatedescription");
                Integer index = cpphotos.indexOf(tobeupdated);
                
                cpphotos.get(index).setDescription(updateddescription);
                cp.setPhotos(cpphotos);
                checkpointFacade.edit(cp);
            }
            if(commentformvalue == null){}
            else if(commentformvalue.startsWith("hiddenpostcomment")){
                String s = request.getParameter("commentform");
                Integer photoid = Integer.parseInt((s.substring(s.lastIndexOf('t') + 1)));
                Photo toaddcomment = photoFacade.find(photoid);
                
                String ctext = request.getParameter("postcomment");
                
                Comment comment = new Comment();
                comment.setText(ctext);
                comment.setPhoto(toaddcomment);
                comment.setUser(user);
                
                List<Comment> pcomments = toaddcomment.getComments();
                pcomments.add(comment);
                toaddcomment.setComments(pcomments);
                
                List<Photo> cpphotos = cp.getPhotos();
                Integer photoindex = cpphotos.indexOf(toaddcomment);
                cpphotos.set(photoindex, toaddcomment);
                
                cp.setPhotos(cpphotos);
                checkpointFacade.edit(cp);
                RoadTripUser photoUser = toaddcomment.getUser();
                Notification notification2 = new Notification();
                notification2.setText("Somebody commented on your photo.");
                notification2.setUser(photoUser);
                notificationFacade.create(notification2);
                
                photoUser.getNotifications().add(notification2);
                userFacade.edit(photoUser);

            }
            if (user.getFollowCp().contains(cp)) {
                request.setAttribute("canFollow", 0);
            } else {
                request.setAttribute("canFollow", 1);
            }

            
            Checkpoint cp2 = checkpointFacade.find(cid);
            List<Photo> photos = cp2.getPhotos();
            photos.isEmpty();
            
            List<Road> roads = cp2.getRoads();
            roads.isEmpty();

            request.setAttribute("Checkpoint", cp2);
            request.setAttribute("photos", photos);
            request.setAttribute("roads", roads);
            
            if(refresh){
                response.sendRedirect(URI);
            }

            
            try {
                request.getRequestDispatcher(url).forward(request, response);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void handleAddPhoto(HttpServletRequest request, RoadTripUser user, Checkpoint cp) {
        Photo photo = new Photo();
        String photodescription = request.getParameter("newdescription");
        String photourl = request.getParameter("newurl");
        
        photo.setDescription(photodescription);
        photo.setUrl(photourl);
        photo.setUser(user);
        photo.setCheckpoint(cp);
        
        List<Photo> addedphoto = cp.getPhotos();
        addedphoto.add(photo);
        cp.setPhotos(addedphoto);
        
        checkpointFacade.edit(cp);
    }

    private void handleFollowButton(RoadTripUser user, Checkpoint cp) {
        List<Checkpoint> followcp = user.getFollowCp();
        followcp.isEmpty();
        if (!followcp.contains(cp)) {
            followcp.add(cp);
        }
        user.setFollowCp(followcp);
        userFacade.edit(user);
    }
    
    private void handleUnFollowButton(RoadTripUser user, Checkpoint cp) {
        List<Checkpoint> followcp = user.getFollowCp();
        followcp.isEmpty();
        if (followcp.contains(cp)) {
            followcp.remove(cp);
        }
        user.setFollowCp(followcp);
        userFacade.edit(user);
    }

    private void handleVisitedButton(RoadTripUser user, Checkpoint cp) {
        List<Checkpoint> visited = user.getVisited();
        visited.isEmpty();
        if (!visited.contains(cp)) {
            visited.add(cp);
        }
        user.setVisited(visited);
        userFacade.edit(user);
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
