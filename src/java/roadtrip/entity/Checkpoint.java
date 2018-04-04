/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.entity;

import java.awt.geom.Point2D;
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
public class Checkpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name="name")
    @NotNull
    private String name;
    
    @Basic(optional = false)
    @Column(name="description")
    @NotNull
    private String description;
    
    
    @Basic(optional = false)
    @Column(name="coordinates")
    @NotNull
    private Point2D.Double coordinates;
    
    @JoinColumn(name = "countryid", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Point2D.Double getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point2D.Double coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof Checkpoint)) {
            return false;
        }
        Checkpoint other = (Checkpoint) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "roadtrip.entity.Checkpoint[ id=" + id + " ]";
    }
    
}
