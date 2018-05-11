/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadtrip.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michael
 */
@Entity
public class Checkpoint implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name="x")
    @NotNull
    private Double x;
    
    @ManyToMany
    @JoinTable(name="isOn")
    private List<Road> roads;
    
    
    @Basic(optional = false)
    @Column(name="y")
    @NotNull
    private Double y;

    @JoinColumn(name = "countryid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Country country;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "checkpoint")
    private List<Photo> photos;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "checkpoint")
    private List<CheckpointRating> ratings;

    public List<CheckpointRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<CheckpointRating> ratings) {
        this.ratings = ratings;
    }
    
    public Double getAverageRating() {
        Double val = 0.0;
        for(CheckpointRating it : ratings) {
            val += it.getRatingvalue();
        }
        if(ratings.isEmpty()) {
            return -1.0;
        }
        val = val/ratings.size();
        return val;
        
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
    
    public double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
    
   public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
