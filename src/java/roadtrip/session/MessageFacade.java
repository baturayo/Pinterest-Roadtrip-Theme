/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.session;

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

    public MessageFacade() {
        super(Message.class);
    }
    
}
