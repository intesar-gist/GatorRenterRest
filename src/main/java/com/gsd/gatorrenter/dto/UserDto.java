package com.gsd.gatorrenter.dto;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Intesar on 3/4/2017.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "userDetails")
public class UserDto implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    @XmlTransient
    private Timestamp created;
    private Integer isActive;

    @XmlElement(name = "userRole")
    private UserRoleDto userRoleDto;

    public UserDto() {
    }

    public UserDto(int id, String firstName, String lastName, String email,  String password, String address, String city,
                   Timestamp created, Integer isActive, UserRoleDto userRoleDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.created = created;
        this.isActive = isActive;
        this.userRoleDto = userRoleDto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public UserRoleDto getUserRoleDto() {
        return userRoleDto;
    }

    public void setUserRoleDto(UserRoleDto userRoleDto) {
        this.userRoleDto = userRoleDto;
    }
}
