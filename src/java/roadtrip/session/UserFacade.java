/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.session;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
    
    public Integer getUserID(String username) {
        List<Integer> lookUp = em.createQuery("SELECT u.id FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .getResultList();
        if (lookUp.isEmpty()) {
            return -1;
        }
        return lookUp.get(0);
    }
    public void blockUser(User loggedInUser, User blockedUser){
        // Update Blocked Table        
        List<User> blocked_list;
        blocked_list = loggedInUser.getBlocked();
        blocked_list.add(0, blockedUser);
        loggedInUser.setBlocked(blocked_list);
        edit(loggedInUser);
    }
    
    public void unblockUser(User loggedInUser, User blockedUser){
        System.out.println("cdascsadcdscsd");
        // Update Blocked Table        
        List<User> blocked_list;
        blocked_list = loggedInUser.getBlocked();
        for (Iterator<User> iter = blocked_list.listIterator(); iter.hasNext(); ) {
            User user = iter.next();
            if (Objects.equals(user.getId(), blockedUser.getId())) {
                iter.remove();
            }
        }
        loggedInUser.setBlocked(blocked_list);
        edit(loggedInUser);  
    }
    
    public void followUser(User follower, User followee){
        // Update Followee Table        
        List<User> followee_list;
        followee_list = follower.getFollowee();
        followee_list.add(0, followee);
        follower.setFollowee(followee_list);
        edit(follower);
        
        // Update Follower Table
        List<User> follower_list;
        follower_list = followee.getFollower();
        follower_list.add(0, follower);
        followee.setFollower(follower_list);
        edit(followee);

    }
    
    public void unfollowUser(User follower, User followee){
        // Update FOLLOWEE table
        List<User> followee_list;
        followee_list = follower.getFollowee();
        for (Iterator<User> iter = followee_list.listIterator(); iter.hasNext(); ) {
            User user = iter.next();
            if (Objects.equals(user.getId(), followee.getId())) {
                iter.remove();
            }
        }
        follower.setFollowee(followee_list);
        edit(follower);
        
        // Update FOLLOWER Table
        List<User> follower_list;
        follower_list = followee.getFollower();
        for (Iterator<User> iter = follower_list.listIterator(); iter.hasNext(); ) {
            User user = iter.next();
            if (Objects.equals(user.getId(), follower.getId())) {
                iter.remove();
            }
        }
        followee.setFollower(follower_list);
        edit(followee);
    }
    
    public Boolean checkFollowUser(User follower, User followee){
        List<User> followee_list;
        followee_list = follower.getFollowee();
        
        for (User user : followee_list) {
            if (Objects.equals(user.getId(), followee.getId())){
                return true;
            }
        }
       return false;
    }
    
    public Boolean checkBlockedUser(User loggedInUser, User visitedUser){
        List<User> blocked_list;
        blocked_list = visitedUser.getBlocked();
        
        for (User user : blocked_list) {
            if (Objects.equals(user.getId(), loggedInUser.getId())){
                return true;
            }
        }
       return false;
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
    
    public void makeAdmin(User visitedUser, Boolean isAdmin) {
        visitedUser.setIsAdmin(isAdmin);
        edit(visitedUser);
    }
    
    @Override
    public void create(User user) {
        try {
            em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
}
