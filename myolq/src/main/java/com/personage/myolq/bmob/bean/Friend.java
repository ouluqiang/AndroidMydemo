package com.personage.myolq.bmob.bean;

import cn.bmob.v3.BmobObject;

/**好友表
 * @author smile
 * @project Friend
 * @date 2016-04-26
 */
public class Friend extends BmobObject {

    private User user;
    private User friendUser;

//    //拼音
//    private transient String pinyin;
//
//    public String getPinyin() {
//        return pinyin;
//    }
//
//    public void setPinyin(String pinyin) {
//        this.pinyin = pinyin;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriendUser() {
        return friendUser;
    }

    public void setFriendUser(User friendUser) {
        this.friendUser = friendUser;
    }


//    private String name;   //显示的数据
    private String sortLetters;  //显示数据拼音的首字母
//
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
    public String getSortLetters() {
        return sortLetters;
    }
    public void setSortLetters(String sortLetters) {
        this.sortLetters = sortLetters;
    }
}
