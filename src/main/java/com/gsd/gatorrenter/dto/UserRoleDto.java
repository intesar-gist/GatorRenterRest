package com.gsd.gatorrenter.dto;

import java.io.Serializable;

/**
 * Created by Intesar on 3/4/2017.
 */
public class UserRoleDto implements Serializable {

    private int id;
    private String roleName;
    private String roleDescription;

    public UserRoleDto(int id, String roleName, String roleDescription) {
        this.id = id;
        this.roleName = roleName;
        this.roleDescription = roleDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
