package es.eoi.java2022.recuerdamelon.dto;

import java.util.List;

public class BusinessDTO {
    private Integer id;

    private String name;

    private String email;

    private byte[] avatar;

    private List<UserDTO> user;

    private TaskDTO task;
    private BusinessUserDTO businessUser;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public List<UserDTO> getUser() {
        return user;
    }

    public void setUser(List<UserDTO> user) {
        this.user = user;
    }

    public BusinessUserDTO getBusinessUser() {
        return businessUser;
    }

    public void setBusinessUser(BusinessUserDTO businessUser) {
        this.businessUser = businessUser;
    }

    public TaskDTO getTask() {
        return task;
    }

    public void setTask(TaskDTO task) {
        this.task = task;
    }
}
