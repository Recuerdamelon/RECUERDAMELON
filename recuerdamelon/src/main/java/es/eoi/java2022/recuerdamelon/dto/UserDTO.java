package es.eoi.java2022.recuerdamelon.dto;

import es.eoi.java2022.recuerdamelon.data.entity.Message;

import java.util.List;

public class UserDTO {
    private Integer id;

    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;

    private String repassword;
    private boolean active;
    private String avatar;

    private List<UserRoleDTO> roles;

    private List<TaskDTO> task;

    private BusinessDTO business;

    private BusinessUserDTO businessUser;

    private List<Message> messages;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
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

    public BusinessDTO getBusiness() {
        return business;
    }

    public void setBusiness(BusinessDTO business) {
        this.business = business;
    }

    public BusinessUserDTO getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(BusinessUserDTO businessUser) {
        this.businessUser = businessUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}

