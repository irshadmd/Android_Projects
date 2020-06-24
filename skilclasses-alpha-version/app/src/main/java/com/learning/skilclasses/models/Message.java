package com.learning.skilclasses.models;

public class Message {
    private String message;
    private String course;
    private String user_id;
    private String sentat;
    private String image;

    public Message(String message, String image, String course, String user_id, String sentat) {
        this.message = message;
        this.course = course;
        this.user_id = user_id;
        this.sentat = sentat;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSentat() {
        return sentat;
    }

    public void setSentat(String sentat) {
        this.sentat = sentat;
    }

    public Message() {
    }
}
