package edu.dam.pm.yatamap.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private List<Task> tasks;

    public User(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.tasks = new ArrayList<>();
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
}
