package es.eoi.java2022.recuerdamelon.dto;

import es.eoi.java2022.recuerdamelon.data.entity.Mensajes;

import java.util.List;

public class UserDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private boolean active;
    private byte[] avatar;

    private List<UserRoleDTO> roles;

    private List<TaskDTO> task;

    private List<BusinessDTO> business;

    private BusinessUserDTO businessUser;

    private List<Mensajes> mensajes;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public List<UserRoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleDTO> roles) {
        this.roles = roles;
    }

    public List<TaskDTO> getTask() {
        return task;
    }

    public void setTask(List<TaskDTO> task) {
        this.task = task;
    }

    public List<BusinessDTO> getBusiness() {
        return business;
    }

    public void setBusiness(List<BusinessDTO> business) {
        this.business = business;
    }

    public BusinessUserDTO getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(BusinessUserDTO businessUser) {
        this.businessUser = businessUser;
    }

    public List<Mensajes> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensajes> mensajes) {
        this.mensajes = mensajes;
    }
}
