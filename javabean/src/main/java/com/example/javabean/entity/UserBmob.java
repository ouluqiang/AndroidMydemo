package com.example.javabean.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class UserBmob extends BmobUser{

    private String nickname;
    private String sex;
    private String age;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
