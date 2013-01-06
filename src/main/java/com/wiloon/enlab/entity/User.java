package com.wiloon.enlab.entity;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 12/16/12
 * Time: 3:29 PM
 */

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

public class User {

    private Long id;
    private String userName;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<Role>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password for this user.
     *
     * @return this user's password
     */

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
