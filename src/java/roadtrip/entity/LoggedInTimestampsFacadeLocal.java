/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.entity;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author cekef
 */
@Local
public interface LoggedInTimestampsFacadeLocal {

    void create(LoggedInTimestamps loggedInTimestamps);

    void edit(LoggedInTimestamps loggedInTimestamps);

    void remove(LoggedInTimestamps loggedInTimestamps);

    LoggedInTimestamps find(Object id);

    List<LoggedInTimestamps> findAll();

    List<LoggedInTimestamps> findRange(int[] range);

    int count();
    
}
