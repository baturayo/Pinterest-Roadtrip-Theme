/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
        
/**
 *
 * @author cekef
 */
@Entity
public class LoggedInTimestamps implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LoggedInTimestampsPK loggedInTimestampsPK;
    
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user_id;
    
    @Basic(optional=false)
    private java.sql.Timestamp moment;
    
    public LoggedInTimestamps(){
    }
    
    public LoggedInTimestamps(LoggedInTimestampsPK loggedInTimestampsPK){
        this.loggedInTimestampsPK = loggedInTimestampsPK;
    }
    
    public LoggedInTimestamps(int user_id, java.sql.Timestamp moment){
        this.loggedInTimestampsPK = new LoggedInTimestampsPK(user_id,moment);
    }

    public LoggedInTimestampsPK getLoggedInTimestampsPK() {
        return loggedInTimestampsPK;
    }

    public void setLoggedInTimestampsPK(LoggedInTimestampsPK loggedInTimestampsPK) {
        this.loggedInTimestampsPK = loggedInTimestampsPK;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Timestamp getMoment() {
        return moment;
    }

    public void setMoment(Timestamp moment) {
        this.moment = moment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loggedInTimestampsPK != null ? loggedInTimestampsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoggedInTimestamps)) {
            return false;
        }
        LoggedInTimestamps other = (LoggedInTimestamps) object;
        return !((this.loggedInTimestampsPK == null && other.loggedInTimestampsPK != null) || (this.loggedInTimestampsPK != null && !this.loggedInTimestampsPK.equals(other.loggedInTimestampsPK)));
    }

    @Override
    public String toString() {
        return "roadtrip.entity.LoggedInTimestamps[ loggedInTimestampsPK=" + loggedInTimestampsPK + " ]";
    }
    
}
