package com.wiloon.enlab.entity;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 12/16/12
 * Time: 3:31 PM
 * To change this template use File | Settings | File Templates.
 */


import java.util.Set;

/**
 * Model object that represents a security role.
 */
public class Role {

    private Long id;

    private String name;

    private String description;

    private Set<String> permissions;

    protected Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

}
