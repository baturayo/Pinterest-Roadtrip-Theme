/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.controller;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import roadtrip.entity.Notification;
import roadtrip.entity.RoadTripUser;
import roadtrip.session.LoggedInTimestampsFacade;
import roadtrip.session.NotificationFacade;
import roadtrip.session.TitleFacade;
import roadtrip.session.UserFacade;

/**
 *
 * @author cekef
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/logout", "/register", "/login"})
public class LoginServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    @EJB
    private LoggedInTimestampsFacade timestampFacade;

    @EJB
    private TitleFacade titleFacade;
    
    @EJB
    private NotificationFacade notificationFacade;

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
        String url = "";
        // use RequestDispatcher to forward request internally
        if (userPath.equals("/register")) {
            url = "/WEB-INF/login/register.jsp";
        }
        if (userPath.equals("/logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("");
            return;
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

        String userPath = request.getServletPath();

        if (userPath.equals("/login")) {

            if (request.getParameter("formName").equals("LoginForm")) {
                String email = request.getParameter("username");
                String password = request.getParameter("password");
                Integer id = userFacade.Login(email, password);
                if (id.equals(-1)) {
                    request.setAttribute("loginError", "Incorrect password or email");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    return;
                } else {
                    timestampFacade.AddNew(id);
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", id);
                    RoadTripUser user = userFacade.find(id);
                    session.setAttribute("loggedinuser", user);

                    Integer score = 0;
                    score = user.getAchievements().stream().map((ach) -> ach.getPoints()).reduce(score, Integer::sum);

                    session.setAttribute("title", titleFacade.getTitle(score));
                    try {
                        response.sendRedirect("");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    return;
                }

            }

        } else if (userPath.equals("/register")) {
            if (request.getParameter("formName").equals("RegisterForm")) {
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
                RoadTripUser user = new RoadTripUser();
                user.setFirstname(firstName);
                user.setSecondname(lastName);
                user.setCountry(country);
                user.setGender(genderDB);
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);

                Boolean failed = false;

                if (!userFacade.checkUniqueEmail(email)) {
                    failed = true;
                    request.setAttribute("error", "E-mail address is already in use.");
                }
                if (!userFacade.checkUniqueUsername(username)) {
                    failed = true;
                    request.setAttribute("error", "Username is already in use.");
                }
                if (failed) {
                    String url = "/WEB-INF/login/register.jsp";
                    try {
                        request.getRequestDispatcher(url).forward(request, response);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    userFacade.create(user);
                    Integer id = userFacade.getUserID(username);
                    user = userFacade.find(id);
                    timestampFacade.AddNew(id);
                    HttpSession session = request.getSession();
                    session.setAttribute("userId", id);
                    Integer score = 0;
                    Notification notification = new Notification();
                    notification.setText("Welcome to our website");
                    notification.setUser(user);
                    notificationFacade.create(notification);
                
                    user.getNotifications().add(notification);
                    userFacade.edit(user);
                    session.setAttribute("loggedinuser", user);

                    session.setAttribute("title", titleFacade.getTitle(score));
                    try {
                        response.sendRedirect("/RoadTrip");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                return;
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
