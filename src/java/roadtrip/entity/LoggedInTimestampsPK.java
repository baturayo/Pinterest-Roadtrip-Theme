/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author cekef
 */
@Embeddable
public class LoggedInTimestampsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "moment", columnDefinition="DATETIME default NOW()")
    private java.sql.Timestamp moment;

    public Timestamp getMoment() {
        return moment;
    }

    public void setMoment(Timestamp moment) {
        this.moment = moment;
    }

    public LoggedInTimestampsPK() {
    }

    public LoggedInTimestampsPK(int userId, java.sql.Timestamp moment) {
        this.userId = userId;
        this.moment = moment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userId;
        hash += (int) moment.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoggedInTimestampsPK)) {
            return false;
        }
        LoggedInTimestampsPK other = (LoggedInTimestampsPK) object;
        if (this.userId != other.userId) {
            return false;
        }
        return this.moment == other.moment;
    }

    @Override
    public String toString() {
        return "roadtrip.entity.LoggedInTimestampsPK[userId=" + userId + ", moment=" + moment + "]";
    }
}
