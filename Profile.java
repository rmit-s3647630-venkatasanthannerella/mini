package com.example;


import java.util.ArrayList;
import java.util.List;

public class Profile {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    List<Friend> mFriendList = new ArrayList<>();
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Friend> getmFriendList() {
        return mFriendList;
    }

    public void setmFriendList(List<Friend> mFriendList) {
        this.mFriendList = mFriendList;
    }
}
