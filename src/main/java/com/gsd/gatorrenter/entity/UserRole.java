package com.gsd.gatorrenter.entity;

import javax.persistence.*;

/**
 * Created by Intesar on 3/3/2017.
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    private int id;
    private String roleName;
    private String roleDescription;

    public UserRole() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_description")
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (id != userRole.id) return false;
        if (roleName != null ? !roleName.equals(userRole.roleName) : userRole.roleName != null) return false;
        if (roleDescription != null ? !roleDescription.equals(userRole.roleDescription) : userRole.roleDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (roleDescription != null ? roleDescription.hashCode() : 0);
        return result;
    }
}
