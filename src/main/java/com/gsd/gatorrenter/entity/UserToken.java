package com.gsd.gatorrenter.entity;

import com.gsd.gatorrenter.dto.UserTokenDto;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Intesar on 3/5/2017.
 */
@Entity
@Table(name = "user_token")
@NamedQueries({
        @NamedQuery(name = "UserToken.searchByAccessTokenUserId", query = "select distinct ut from UserToken ut where ut.active = 1 AND ut.accessToken = :accessToken " +
                "AND ut.user.id = :userId order by ut.id ASC"),
        @NamedQuery(name = "UserToken.getUserTokensByUserId", query = "select ut from UserToken ut where ut.user.id = :userId")
})
public class UserToken {

    private int id;
    private String accessToken;
    private Integer active;
    private Timestamp creationDate;
    private Timestamp lastUpdated;
    private User user;

    public UserToken() {
    }

    public UserToken(String accessToken, Integer active, Timestamp creationDate, Timestamp lastUpdated, User user) {
        this.accessToken = accessToken;
        this.active = active;
        this.creationDate = creationDate;
        this.lastUpdated = lastUpdated;
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "access_token")
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Basic
    @Column(name = "active")
    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    @Basic
    @Column(name = "creation_date")
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "last_updated")
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uid")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserTokenDto asDto() {
        UserTokenDto userTokenDto = new UserTokenDto(id, accessToken, active, creationDate, lastUpdated,
                user.asDto());
        return userTokenDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserToken userToken = (UserToken) o;

        if (id != userToken.id) return false;
        if (accessToken != null ? !accessToken.equals(userToken.accessToken) : userToken.accessToken != null)
            return false;
        if (active != null ? !active.equals(userToken.active) : userToken.active != null) return false;
        if (creationDate != null ? !creationDate.equals(userToken.creationDate) : userToken.creationDate != null)
            return false;
        if (lastUpdated != null ? !lastUpdated.equals(userToken.lastUpdated) : userToken.lastUpdated != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (accessToken != null ? accessToken.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }
}
