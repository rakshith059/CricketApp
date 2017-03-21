package com.rakshith.cricketapp.cricketAdmin.models;

/**
 * Created by rakshith on 3/11/17.
 */
public class MemberItem {
    private String name;
    private String role;

    public MemberItem(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public MemberItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
