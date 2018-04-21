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
import roadtrip.entity.Message;
import roadtrip.entity.User;

/**
 *
 * @author cekef
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    
    public List<Integer> getMessages(User senderUserId, User receiverUserId) {
        System.out.println(senderUserId+ "sender:");
        System.out.println(receiverUserId+ "receiver:");
        List<Integer> lookUp;
        lookUp = em.createQuery("SELECT m.id FROM Message m WHERE m.sender = :senderUserId AND m.receiver = :receiverUserId")
                .setParameter("senderUserId", senderUserId)
                .setParameter("receiverUserId", receiverUserId)
                .getResultList();
        return lookUp;
    }
    
}
