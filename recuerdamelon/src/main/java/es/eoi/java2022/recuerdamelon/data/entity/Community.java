package es.eoi.java2022.recuerdamelon.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "community_has_user",
            joinColumns = @JoinColumn(name = "community_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;
    private Integer admin;


//To find communities associated to a User
    public Set<Community> userId (User user){
        return user.getCommunities();
    }
//To add user in a community
public void addUser(User user) {
    this.users.add(user);
    user.getCommunities().add(this);
}

//    public void removeTag(long tagId) {
//        User tag = this.tags.stream().filter(t -> t.getId() == tagId).findFirst().orElse(null);
//        if (tag != null) {
//            this.tags.remove(tag);
//            tag.getTutorials().remove(this);
//        }
//    }

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }
}
