package edu.dam.pm.yatamap.classes;

import java.util.Date;
import java.util.UUID;

public class Task {
    private String id;
    private String name;
    private String description;
    private TaskType type;
    private int priority;
    private boolean done;
    private Date scheduledDate;

    public Task(String name, String description, TaskType type, int priority, boolean done, Date scheduledDate) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.type = type;
        this.priority = priority;
        this.done = done;
        this.scheduledDate = scheduledDate;
    }

    public Task(String id, String name, String description, TaskType type, int priority, boolean done, Date scheduledDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.priority = priority;
        this.done = done;
        this.scheduledDate = scheduledDate;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskType getType() { return type; }
    public void setType(TaskType type) { this.type = type; }

    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }

    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    public Date getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(Date scheduledDate) { this.scheduledDate = scheduledDate; }
}
