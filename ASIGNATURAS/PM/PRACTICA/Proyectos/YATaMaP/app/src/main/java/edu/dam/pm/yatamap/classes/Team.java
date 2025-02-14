package edu.dam.pm.yatamap.classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team {
    private String id;
    private String name;
    private List<User> members;

    public Team(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.members = new ArrayList<>();
    }

    public Team(String id, String name, List<User> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<User> getMembers() { return members; }
    public void setMembers(List<User> members) { this.members = members; }

    public  void  addMember(User user) { members.add(user); }
    public void removeMember(User user) { members.remove(user); }
}

