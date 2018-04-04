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
import roadtrip.entity.Title;

/**
 *
 * @author cekef
 */
@Stateless
public class TitleFacade extends AbstractFacade<Title> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TitleFacade() {
        super(Title.class);
    }
    
    public String getTitle(Integer score){
        List<String> titles= em.createQuery("SELECT t.name FROM Title t WHERE t.requiredPoints <= :score ORDER BY t.requiredPoints DESC")
                .setParameter("score", score)
                .getResultList();
        return titles.get(0);
    }
    
}
