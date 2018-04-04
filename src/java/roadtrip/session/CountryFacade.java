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
import roadtrip.entity.Country;

/**
 *
 * @author cekef
 */
@Stateless
public class CountryFacade extends AbstractFacade<Country> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CountryFacade() {
        super(Country.class);
    }
    
    public Integer getId(String code){
        List<Integer> lookUp = em.createQuery("SELECT c.id FROM Country c WHERE c.code = :code")
                .setParameter("code", code)
                .getResultList();
        if (lookUp.isEmpty()) {
            return -1;
        }
        return lookUp.get(0);

    }
    
}
