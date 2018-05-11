/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michael
 */
@Entity
public class CheckpointRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RoadTripUser user;
    
    @Basic(optional = false)
    @Column(name="ratingvalue")
    @NotNull
    private Double ratingvalue;
    
    @JoinColumn(name = "checkpointid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Checkpoint checkpoint;

    public Checkpoint getCheckpoint() {
        return checkpoint;
    }

    public void setCheckpoint(Checkpoint checkpoint) {
        this.checkpoint = checkpoint;
    }
    
    public RoadTripUser getUser() {
        return user;
    }

    public void setUser(RoadTripUser user) {
        this.user = user;
    }

    public Double getRatingvalue() {
        return ratingvalue;
    }

    public void setRatingvalue(Double ratingvalue) {
        this.ratingvalue = ratingvalue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CheckpointRating)) {
            return false;
        }
        CheckpointRating other = (CheckpointRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "roadtrip.entity.CheckpointRating[ id=" + id + " ]";
    }
    
}
