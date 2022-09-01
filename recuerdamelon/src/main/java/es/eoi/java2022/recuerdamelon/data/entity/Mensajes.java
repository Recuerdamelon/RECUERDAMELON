package es.eoi.java2022.recuerdamelon.data.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
public class Mensajes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, name = "texto")
    private String mensaje;
    @ManyToMany
    @JoinTable(
            name = "mensajes_has_user",
            joinColumns = @JoinColumn(name = "mensajes_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

    private String date;
    private String title;
    private String sender;

    private boolean deleted;
    private boolean saved;
    private boolean sent;
    private boolean recieved;
    private boolean invitation;
    private String community;

    private boolean acepted;

    private Integer reciever;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isRecieved() {
        return recieved;
    }

    public void setRecieved(boolean recieved) {
        this.recieved = recieved;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isInvitation() {
        return invitation;
    }

    public void setInvitation(boolean invitation) {
        this.invitation = invitation;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public boolean isAcepted() {
        return acepted;
    }

    public void setAcepted(boolean acepted) {
        this.acepted = acepted;
    }

    public Integer getReciever() {
        return reciever;
    }

    public void setReciever(Integer reciever) {
        this.reciever = reciever;
    }
}