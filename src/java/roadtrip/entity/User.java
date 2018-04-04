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
import javax.validation.constraints.NotNull;

/**
 *
 * @author baturay
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
   
    @Basic(optional = false)
    @NotNull
    @Column(name = "email", unique=true)
    private String email;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "username", unique=true)
    private String username;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "firstname")
    private String firstname;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "secondname")
    private String secondname;
    
    @Basic(optional = true)
    @Column(name = "country")
    private String country;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender")
    private Integer gender;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "password")
    private String password;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getCountry() {
        return country;
    }

    public Integer getGender() {
        return gender;
    }
    
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        this.email = e;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "roadtrip.entity.User[ id=" + id + " ]";
    }
    
}
