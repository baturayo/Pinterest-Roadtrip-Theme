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
import roadtrip.entity.Photo;

/**
 *
 * @author Michael
 */
@Stateless
public class PhotoFacade extends AbstractFacade<Photo> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhotoFacade() {
        super(Photo.class);
    }
    
   public List<Photo> getCheckpointPhotos(Integer cid) {
        List<Photo> lookUp;
        lookUp = em.createQuery("SELECT p.id FROM Photo p WHERE p.checkpoint = :cid")
                .setParameter("cid", cid)
                .getResultList();
        return lookUp;

    }
    
}
