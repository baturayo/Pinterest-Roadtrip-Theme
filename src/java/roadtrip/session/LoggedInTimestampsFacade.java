/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import roadtrip.entity.LoggedInTimestamps;
import roadtrip.entity.LoggedInTimestampsPK;

/**
 *
 * @author cekef
 */
@Stateless
public class LoggedInTimestampsFacade extends AbstractFacade<LoggedInTimestamps> {

    @PersistenceContext(unitName = "RoadTripPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoggedInTimestampsFacade() {
        super(LoggedInTimestamps.class);
    }
    
    public void AddNew(Integer id){
        LoggedInTimestampsPK PK = new LoggedInTimestampsPK();
        PK.setUserId(id);
        
        LoggedInTimestamps toAdd = new LoggedInTimestamps(PK);
        create(toAdd);
    }
    
}
