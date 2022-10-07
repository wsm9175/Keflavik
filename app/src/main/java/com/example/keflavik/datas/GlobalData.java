package com.example.keflavik.datas;

import com.example.keflavik.datas.UserData;

public class GlobalData {
    private static UserData GlobalUser;

    public UserData getGlobalUser(){
        if (GlobalUser == null){
            GlobalUser = new UserData();
        }
        return GlobalUser;
    }

    @Override
    public String toString() {
        return getGlobalUser().name+"이메일"+getGlobalUser().email;
    }

    /*public void setGlobalUser(User globalUser) {
        GlobalUser = globalUser;
    }*/
}
