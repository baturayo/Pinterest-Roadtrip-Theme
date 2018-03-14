/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.session;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author cekef
 */
@Stateless
public class LoginBean {
    
//    @EJB
//    UserFacade userFacade;
//    
    
    
    public Integer Login(String email, String Password){
//        List<Integer> Lookup = userFacade.Login(email, Password);
//        if (Lookup.isEmpty()){
//            return -1;
//        }
//        // TODO: Add timestamp
//        return Lookup.get(0);
    return 0;
    }
}
