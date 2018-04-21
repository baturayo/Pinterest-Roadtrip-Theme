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
    
    public void createMessage(Integer senderUserId, Integer receiverUserId, String text_message){
        Message message = new Message();
        message.setMessage(text_message);
        message.setReceiverUserId(receiverUserId);
        message.setSenderUserId(senderUserId);
        create(message);
    }
    
    public List<Integer> getMessages(Integer senderUserId, Integer receiverUserId) {
        System.out.println(senderUserId+ "sender:");
        System.out.println(receiverUserId+ "receiver:");
        List<Integer> lookUp;
        lookUp = em.createQuery("SELECT m.id FROM Message m WHERE m.senderUserId = :senderUserId AND m.receiverUserId = :receiverUserId")
                .setParameter("senderUserId", senderUserId)
                .setParameter("receiverUserId", receiverUserId)
                .getResultList();
        return lookUp;
    }

    public MessageFacade() {
        super(Message.class);
    }
    
}
