package es.eoi.java2022.recuerdamelon.data.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Business {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nif;
    private byte[] avatar;

    @OneToMany(mappedBy = "empresa")
    private Set<User> users;

    @OneToMany(mappedBy = "business")
    private Set<Task> tasks;

    @OneToOne(mappedBy = "business" )
    private BusinessUser businessUser;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String isNif() {return nif;}

    public void setNif(String nif) {this.nif = nif;}

    public byte[] getAvatar() {return avatar;}

    public void setAvatar(byte[] avatar) {this.avatar = avatar;}

    public Set<User> getUsers() {return users;}

    public void setUsers(Set<User> users) {this.users = users;}


    public BusinessUser getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(BusinessUser businessUser) {
        this.businessUser = businessUser;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


}
