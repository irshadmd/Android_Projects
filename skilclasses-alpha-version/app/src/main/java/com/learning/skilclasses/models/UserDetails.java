package com.learning.skilclasses.models;

public class UserDetails {
    private String id;
    private String name;
    private String username;
    private String password;
    private String login;
    private String course;

    public UserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDetails(String name, String username, String password, String login, String course) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.login = login;
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserDetails() {
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }

    public UserDetails(String username) {
        this.username = username;
    }
}
