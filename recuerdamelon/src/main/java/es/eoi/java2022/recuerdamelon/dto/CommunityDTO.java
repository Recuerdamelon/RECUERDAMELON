package es.eoi.java2022.recuerdamelon.dto;


import es.eoi.java2022.recuerdamelon.data.entity.Community;
import es.eoi.java2022.recuerdamelon.data.entity.User;

import java.io.Serializable;
import java.util.Set;

public class CommunityDTO implements Serializable {

    private Integer id;
    private String name;
    private Integer admin;

    private Set<User> users;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
