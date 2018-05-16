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
import roadtrip.entity.Checkpoint;

/**
 *
 * @author Michael
 */
@Stateless
public class CheckpointFacade extends AbstractFacade<Checkpoint> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CheckpointFacade() {
        super(Checkpoint.class);
    }
    
    public Boolean checkUniqueCheckpointName(String cpname) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT c.name FROM Checkpoint c WHERE c.name = :cpname")
                .setParameter("cpname", cpname)
                .getResultList();
        return lookUp.isEmpty();
    }
    public Boolean checkUniqueCoordinates(Double cpx,Double cpy) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT c.name FROM Checkpoint c WHERE c.x = :cpx AND c.y = :cpy")
                .setParameter("cpx", cpx)
                .setParameter("cpy", cpy)
                .getResultList();
        return lookUp.isEmpty();
    }
    
  public Checkpoint getCheckpointFromName(String cpname) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT c.id FROM Checkpoint c WHERE c.name = :cpname")
                .setParameter("cpname", cpname)
                .getResultList();
        if(!lookUp.isEmpty()) {
            Integer id = Integer.parseInt(lookUp.get(0));
            Checkpoint r = this.find(id);
            return r;
        }
        Checkpoint error = new Checkpoint();
        error.setId(-1);
        error.setName("ERROR");
        return error;
    }
    
    
}
