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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author baturay
 */
@Entity
public class RoadTripUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "isAdmin")
    private Boolean isAdmin = false;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender")
    private Integer gender;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "password")
    private String password;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy = "user")
    private List<LoggedInTimestamps> loggedInTimeStamps;
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="user")
    private List<Notification> notifications;
    
    @OneToMany(mappedBy="sender")
    private List<Message> sent;
    
    @OneToMany(mappedBy="receiver")
    private List<Message> received;
    
    @OneToMany(cascade=CascadeType.DETACH, mappedBy = "user")
    private List<Road> roads;
    
    @ManyToMany(mappedBy="users")
    private List<Achievement> achievements;
    
    @OneToMany
    @JoinTable(name="VISITED")
    private List<Checkpoint> visited;
    
    @OneToMany
    @JoinTable(name="WANTTOVISIT")
    private List<Checkpoint> wanttovisit;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="FOLLOWEE")
    private List<RoadTripUser> followee;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="FOLLOWER")
    private List<RoadTripUser> follower;
    
    @ManyToMany
    @JoinTable(name="BLOCKED")
    private List<RoadTripUser> blocked;
    
    public List<RoadTripUser> getBlocked() {
        return blocked;
    }

    public void setBlocked(List<RoadTripUser> blocked) {
        this.blocked = blocked;
    }
    
    public List<RoadTripUser> getFollowee() {
        return followee;
    }

    public void setFollowee(List<RoadTripUser> followee) {
        this.followee = followee;
    }
    
    public List<RoadTripUser> getFollower() {
        return follower;
    }

    public void setFollower(List<RoadTripUser> follower) {
        this.follower = follower;
    }
    
    public List<Checkpoint> getVisited() {
        return visited;
    }

    public void setVisited(List<Checkpoint> visited) {
        this.visited = visited;
    }

    public List<Checkpoint> getWanttovisit() {
        return wanttovisit;
    }

    public void setWanttovisit(List<Checkpoint> wanttovisit) {
        this.wanttovisit = wanttovisit;
    }

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

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.achievements = achievements;
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

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Message> getSent() {
        return sent;
    }

    public void setSent(List<Message> sent) {
        this.sent = sent;
    }

    public List<Message> getReceived() {
        return received;
    }

    public void setReceived(List<Message> received) {
        this.received = received;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public List<LoggedInTimestamps> getLoggedInTimeStamps() {
        return loggedInTimeStamps;
    }

    public void setLoggedInTimeStamps(List<LoggedInTimestamps> loggedInTimeStamps) {
        this.loggedInTimeStamps = loggedInTimeStamps;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoadTripUser)) {
            return false;
        }
        RoadTripUser other = (RoadTripUser) object;
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
