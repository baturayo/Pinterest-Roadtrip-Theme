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
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {

        super(User.class);
    }
    
    public List Login(String email,String password){
        return em.createNamedQuery( "SELECT id FROM user WHERE email = :email AND password = :password")
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
    }
    @Override
    public void create(User user){
    try {
        em.persist(user); 
    } catch (Exception e) {
        e.printStackTrace();
    }
} 
    
}
