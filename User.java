package com.example;


import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding;

public class User {

    Profile mProfile;

    int mAge;

    List<User> parents = new ArrayList<>();

    List<User> children = new ArrayList<>();

    public List<User> getChildren() {
        return children;
    }

    public void setChildren(List<User> children) {
        this.children = children;
    }

    public List<User> getParents() {
        return parents;
    }

    public void setParents(List<User> parents) {
        this.parents = parents;
    }

    public int getmAge() {
        return mAge;
    }

    public void setmAge(int mAge) {
        this.mAge = mAge;
    }



    public Profile getmProfile() {
        return mProfile;
    }

    public void setmProfile(Profile mProfile) {
        this.mProfile = mProfile;
    }


}
