package com.example.mentalgyaan;

public class Discovers {

    String topicname, topicdes, topicimage;

    public Discovers() {
    }

    public Discovers(String topicname, String topicdes, String topicimage) {
        this.topicname = topicname;
        this.topicdes = topicdes;
        this.topicimage = topicimage;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public String getTopicdes() {
        return topicdes;
    }

    public void setTopicdes(String topicdes) {
        this.topicdes = topicdes;
    }

    public String getTopicimage() {
        return topicimage;
    }

    public void setTopicimage(String topicimage) {
        this.topicimage = topicimage;
    }
}
