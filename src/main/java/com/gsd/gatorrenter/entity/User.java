package com.gsd.gatorrenter.entity;

import com.gsd.gatorrenter.dto.UserDto;
import com.gsd.gatorrenter.dto.UserRoleDto;
import com.gsd.gatorrenter.utils.EntityHelper;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Intesar on 3/3/2017.
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "searchUserByEmail",
                query = "select distinct u from User u where lower(u.email) = :userEmail AND u.isActive = 1 order by u.id ASC")
})
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String city;
    private Timestamp created;
    private Integer isActive;
    private UserRole userRole;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(UserDto userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.address = userDto.getAddress();
        this.city = userDto.getCity();
        this.created = new Timestamp(System.currentTimeMillis());
        this.isActive = 1;
        this.userRole = new UserRole(userDto.getUserRoleDto());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid")
    public int getId() {
        return id;
    }

    public void setId(int uid) {
        this.id = uid;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "created")
    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    @Basic
    @Column(name = "is_active")
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @ManyToOne
    @JoinColumn(name = "user_roles_id", referencedColumnName = "id")
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserDto asDto() {

        UserRoleDto userRoleDto = EntityHelper.isNotNull(userRole) ? userRole.asDto() : null;

        UserDto userDto = new UserDto(id, firstName, lastName, email, password, address, city,
                created, isActive, userRoleDto);

        return userDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (created != null ? !created.equals(user.created) : user.created != null) return false;
        if (isActive != null ? !isActive.equals(user.isActive) : user.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
