package es.eoi.java2022.recuerdamelon.data.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Column(nullable = false)
    private ZonedDateTime taskDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "calendar")
    private Set<Task> tasks;

    @ManyToMany( mappedBy = "calendars")
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public ZonedDateTime getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(ZonedDateTime taskDate) {
        this.taskDate = taskDate;
    }
}
