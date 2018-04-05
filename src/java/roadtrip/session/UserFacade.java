/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import roadtrip.entity.User;

/**
 *
 * @author cekef
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public Integer Login(String email, String password) {
        List<Integer> lookUp = em.createQuery("SELECT u.id FROM User u WHERE u.email = :email AND u.password = :password")
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
        if (lookUp.isEmpty()) {
            return -1;
        }
        return lookUp.get(0);
    }
    
    public String getCurrentUserPassword(Integer id) {
        List<String> lookUp = em.createQuery("SELECT u.password FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .getResultList();
        if (lookUp.isEmpty()) {
            return "";
        }
        return lookUp.get(0);
    }
    
    public String getUserFirstName(String username) {
        List<String> lookUp = em.createQuery("SELECT u.firstname FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .getResultList();
        if (lookUp.isEmpty()) {
            return "";
        }
        return lookUp.get(0);
    }
    
    public String getUserSecondName(String username) {
        List<String> lookUp = em.createQuery("SELECT u.secondname FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .getResultList();
        if (lookUp.isEmpty()) {
            return "";
        }
        return lookUp.get(0);
    }
    
    public Boolean checkUniqueEmail(String email) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT u.email FROM User u WHERE u.email = :email")
                .setParameter("email", email)
                .getResultList();
        return lookUp.isEmpty();
    }
    
    public Boolean checkUniqueUsername(String username) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT u.username FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .getResultList();
        return lookUp.isEmpty();
    }

    @Override
    public void create(User user) {
        try {
            System.out.println("cdasdcsdacsd");
            em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
