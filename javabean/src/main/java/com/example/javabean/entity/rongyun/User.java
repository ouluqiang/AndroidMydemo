package com.example.javabean.entity.rongyun;

/**
 * Created by Administrator on 2016/6/14 0014.
 * id=136241,
 username=123456,
 portrait=http: //www.gravatar.com/avatar/b7dda8b4f861352fb7beb09d96f47cc5?s=82&d=wavatar,
 token=kV2tPaRKYdwhqVVY6wxmYrI6ZiT8q7s0UEaMPWY0lMy1d7fiy/CwHXLhWPSBGEEbXSiPW/U+/s+ukGduTG7UgA==
 */
public class User {

    private String id;
    private String username;
    private String portrait;
    private String token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", portrait='" + portrait + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
