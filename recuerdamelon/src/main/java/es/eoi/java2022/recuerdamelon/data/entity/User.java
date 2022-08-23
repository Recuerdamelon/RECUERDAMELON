package es.eoi.java2022.recuerdamelon.data.entity;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean active;
    private String avatar;

    @OneToMany(mappedBy = "owner")
    private Set<Task> ownedTasks;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY )
    private Set<Task> tasks;



    @ManyToMany
    @JoinTable(
            name = "user_has_calendar",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "calendar_id")
    )
    private Set<Calendar> calendars;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> roles;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @ManyToOne
    private Business business;

    @OneToOne
    @JoinColumn(unique = true)
    private BusinessUser businessUser;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY )
    private Set<Community> communities;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY )
    private Set<Mensajes> mensajes;


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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getAvatarUrl() {
        if (avatar == null || StringUtils.isEmpty(avatar)) return null;
        return "/user-photos/" + id + "/" + avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<Task> getOwnedTasks() {
        return ownedTasks;
    }

    public void setOwnedTasks(Set<Task> ownedTasks) {
        this.ownedTasks = ownedTasks;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(Set<Calendar> calendars) {
        this.calendars = calendars;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public BusinessUser getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(BusinessUser businessUser) {
        this.businessUser = businessUser;
    }


    public Set<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Set<Community> communities) {
        this.communities = communities;
    }

    public Set<Mensajes> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Mensajes> mensajes) {
        this.mensajes = mensajes;
    }
}
