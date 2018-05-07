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
import roadtrip.entity.RoadTripUser;

/**
 *
 * @author cekef
 */
@Stateless
public class UserFacade extends AbstractFacade<RoadTripUser> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(RoadTripUser.class);
    }
    
    public Integer Login(String email, String password) {
        List<Integer> lookUp = em.createQuery("SELECT u.id FROM RoadTripUser u WHERE u.email = :email AND u.password = :password")
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
        if (lookUp.isEmpty()) {
            return -1;
        }
        return lookUp.get(0);
    }
    
    public String getCurrentUserPassword(Integer id) {
        List<String> lookUp = em.createQuery("SELECT u.password FROM RoadTripUser u WHERE u.id = :id")
                .setParameter("id", id)
                .getResultList();
        if (lookUp.isEmpty()) {
            return "";
        }
        return lookUp.get(0);
    }
    
    public Integer getUserID(String username) {
        List<Integer> lookUp = em.createQuery("SELECT u.id FROM RoadTripUser u WHERE u.username = :username")
                .setParameter("username", username)
                .getResultList();
        if (lookUp.isEmpty()) {
            return -1;
        }
        return lookUp.get(0);
    }
    public void blockUser(RoadTripUser loggedInUser, RoadTripUser blockedUser){
        // Update Blocked Table        
        List<RoadTripUser> blocked_list;
        blocked_list = loggedInUser.getBlocked();
        blocked_list.add(0, blockedUser);
        loggedInUser.setBlocked(blocked_list);
        edit(loggedInUser);
    }
    
    public void unblockUser(RoadTripUser loggedInUser, RoadTripUser blockedUser){
        System.out.println("cdascsadcdscsd");
        // Update Blocked Table        
        List<RoadTripUser> blocked_list;
        blocked_list = loggedInUser.getBlocked();
        for (Iterator<RoadTripUser> iter = blocked_list.listIterator(); iter.hasNext(); ) {
            RoadTripUser user = iter.next();
            if (Objects.equals(user.getId(), blockedUser.getId())) {
                iter.remove();
            }
        }
        loggedInUser.setBlocked(blocked_list);
        edit(loggedInUser);  
    }
    
    public void followUser(RoadTripUser follower, RoadTripUser followee){
        // Update Followee Table        
        List<RoadTripUser> followee_list;
        followee_list = follower.getFollowee();
        followee_list.add(0, followee);
        follower.setFollowee(followee_list);
        edit(follower);
        
        // Update Follower Table
        List<RoadTripUser> follower_list;
        follower_list = followee.getFollower();
        follower_list.add(0, follower);
        followee.setFollower(follower_list);
        edit(followee);

    }
    
    public void unfollowUser(RoadTripUser follower, RoadTripUser followee){
        // Update FOLLOWEE table
        List<RoadTripUser> followee_list;
        followee_list = follower.getFollowee();
        for (Iterator<RoadTripUser> iter = followee_list.listIterator(); iter.hasNext(); ) {
            RoadTripUser user = iter.next();
            if (Objects.equals(user.getId(), followee.getId())) {
                iter.remove();
            }
        }
        follower.setFollowee(followee_list);
        edit(follower);
        
        // Update FOLLOWER Table
        List<RoadTripUser> follower_list;
        follower_list = followee.getFollower();
        for (Iterator<RoadTripUser> iter = follower_list.listIterator(); iter.hasNext(); ) {
            RoadTripUser user = iter.next();
            if (Objects.equals(user.getId(), follower.getId())) {
                iter.remove();
            }
        }
        followee.setFollower(follower_list);
        edit(followee);
    }
    
    public Boolean checkFollowUser(RoadTripUser follower, RoadTripUser followee){
        List<RoadTripUser> followee_list;
        followee_list = follower.getFollowee();
        
        for (RoadTripUser user : followee_list) {
            if (Objects.equals(user.getId(), followee.getId())){
                return true;
            }
        }
       return false;
    }
    
    public Boolean checkBlockedUser(RoadTripUser loggedInUser, RoadTripUser visitedUser){
        List<RoadTripUser> blocked_list;
        blocked_list = visitedUser.getBlocked();
        
        for (RoadTripUser user : blocked_list) {
            if (Objects.equals(user.getId(), loggedInUser.getId())){
                return true;
            }
        }
       return false;
    }
    
    public Boolean checkUniqueEmail(String email) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT u.email FROM RoadTripUser u WHERE u.email = :email")
                .setParameter("email", email)
                .getResultList();
        return lookUp.isEmpty();
    }
    
    public Boolean checkUniqueUsername(String username) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT u.username FROM RoadTripUser u WHERE u.username = :username")
                .setParameter("username", username)
                .getResultList();
        return lookUp.isEmpty();
    }
    
    public void makeAdmin(RoadTripUser visitedUser, Boolean isAdmin) {
        visitedUser.setIsAdmin(isAdmin);
        edit(visitedUser);
    }
    
    @Override
    public void create(RoadTripUser user) {
        try {
            em.persist(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
}
