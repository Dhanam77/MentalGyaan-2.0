package com.example.mentalgyaan;

public class Helps {

    private String location, contact, name;

    public Helps() {
    }

    public Helps(String location, String contact, String name) {
        this.location = location;
        this.contact = contact;
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
