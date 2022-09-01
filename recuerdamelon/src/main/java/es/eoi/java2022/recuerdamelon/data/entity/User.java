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

    @Column(nullable = true)
    private String birthday;

    @Column(nullable = true)
    private String nationality;


    private boolean business;

    @Column
    private String nif;

    @Column
    private String team;

    @Column(name = "business_avatar")
    private byte[] bavatar;


    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
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


    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Community> communities;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    //    public Set<Task> getOwnedTasks() {
//        return ownedTasks;
//    }
//
//    public void setOwnedTasks(Set<Task> ownedTasks) {
//        this.ownedTasks = ownedTasks;
//    }

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

    public boolean isBusiness() {
        return business;
    }

    public void setBusiness(boolean business) {
        this.business = business;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public byte[] getBavatar() {
        return bavatar;
    }

    public void setBavatar(byte[] bavatar) {
        this.bavatar = bavatar;
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
