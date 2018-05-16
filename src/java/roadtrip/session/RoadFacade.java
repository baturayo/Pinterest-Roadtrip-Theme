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
import roadtrip.entity.Road;

/**
 *
 * @author cekef
 */
@Stateless
public class RoadFacade extends AbstractFacade<Road> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoadFacade() {
        super(Road.class);
    }
    
    
    public Boolean checkUniqueRoadName(String rname) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT r.name FROM Road r WHERE r.name = :rname")
                .setParameter("rname", rname)
                .getResultList();
        return lookUp.isEmpty();
    }
    
   public Road getRoadFromName(String rname) {
        List<String> lookUp;
        lookUp = em.createQuery("SELECT r.id FROM Road r WHERE r.name = :rname")
                .setParameter("rname", rname)
                .getResultList();
        if(!lookUp.isEmpty()) {
            Integer id = Integer.parseInt(lookUp.get(0));
            Road r = this.find(id);
            return r;
        }
        Road error = new Road();
        error.setId(-1);
        error.setName("ERROR");
        return error;
    }
    
    
}
