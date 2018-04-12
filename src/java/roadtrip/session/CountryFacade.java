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
    
    public String getFile(String code){
        List<String> lookUp = em.createQuery("SELECT c.filename FROM Country c WHERE c.code = 'US'")
                //.setParameter("code", code)
                .getResultList();
        System.out.println(lookUp);
        if (lookUp.isEmpty()) {
            return "";
        }
        return lookUp.get(0);

    }
    
}
