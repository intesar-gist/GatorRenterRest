package com.gsd.gatorrenter.dto;

import javax.xml.bind.annotation.*;
import java.sql.Timestamp;

/**
 * Created by Intesar on 3/5/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "loginDetails")
public class UserTokenDto {

    @XmlTransient
    private int id;
    @XmlTransient
    private Integer active;
    @XmlTransient
    private Timestamp creationDate;
    @XmlTransient
    private Timestamp lastUpdated;

    private String accessToken;

    @XmlTransient
    private UserDto userDto;

    public UserTokenDto() {
    }

    public UserTokenDto(int id, String accessToken, Integer active, Timestamp creationDate, Timestamp lastUpdated, UserDto userDto) {
        this.id = id;
        this.accessToken = accessToken;
        this.active = active;
        this.creationDate = creationDate;
        this.lastUpdated = lastUpdated;
        this.userDto = userDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }
}
