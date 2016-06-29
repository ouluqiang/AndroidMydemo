package com.personage.myolq.bmob.bean;


import com.personage.myolq.bmob.db.NewFriend;

import cn.bmob.v3.BmobUser;

/**
 * @author :smile
 * @project:User
 * @date :2016-01-22-18:11
 */
public class User extends BmobUser {

    private String age;
    private String sex;
    private String nickname;
    private String mobile;

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    private String avatar;

    public User(){}

    public User(NewFriend friend){
        setObjectId(friend.getUid());
        setUsername(friend.getName());
        setAvatar(friend.getAvatar());
    }

//    public User(String nickname){
//        this.nickname=nickname;
//
//    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
