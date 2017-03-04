package com.gsd.gatorrenter.dto;

import com.gsd.gatorrenter.entity.UserRole;
import com.gsd.gatorrenter.utils.EntityHelper;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Intesar on 3/4/2017.
 */
public class UserDto implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    private Timestamp created;
    private Integer isActive;
    private UserRoleDto userRole;

    public UserDto() {
    }

    public UserDto(int id, String firstName, String lastName, String email, String address, String city,
                   Timestamp created, Integer isActive, UserRole userRole) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.city = city;
        this.created = created;
        this.isActive = isActive;

        if(EntityHelper.isNotNull(userRole)) {
            this.userRole = new UserRoleDto(userRole.getId(), userRole.getRoleName(), userRole.getRoleDescription());
        }
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

    public UserRoleDto getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoleDto userRole) {
        this.userRole = userRole;
    }
}
