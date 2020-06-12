package com.example.mentalgyaan;

public class Therapies {

    private String image,text, name;

    public Therapies() {
    }

    public Therapies(String image, String text, String name) {
        this.image = image;
        this.text = text;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
