/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.session;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import roadtrip.entity.User;

/**
 *
 * @author baturay
 */
@Stateless
public class Register {
 
    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;
    
    public void placeOrder(String firstName, String lastName, String country, Integer gender, String username, String email, String password) {
        addUser(firstName, lastName, country, gender, username, email, password);
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private void addUser(String firstName, String lastName, String country, 
            Integer gender, String username, String email, String password) {
        
        User user = new User();
        user.setFirstname(firstName);
        user.setSecondname(lastName);
        user.setCountry(country);
        user.setGender(gender);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        em.persist(user);
    }
}
