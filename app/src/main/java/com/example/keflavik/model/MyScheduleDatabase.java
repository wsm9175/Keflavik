package com.example.keflavik.model;

import android.content.Context;

import com.example.keflavik.api.APIList;
import com.example.keflavik.api.ServerAPI;
import com.example.keflavik.viewmodel.MyScheduleViewModel;

import retrofit2.Retrofit;

public class MyScheduleDatabase {
    static Retrofit retrofit;
    static APIList apiList;
    private MyScheduleViewModel.MyScheduleListener databaseListener;
    private static MyScheduleDatabase instance;

    public MyScheduleDatabase getInstance(Context mContext) {
        if(instance == null){
            instance = new MyScheduleDatabase();
            retrofit = ServerAPI.getRetrofit(mContext);
            apiList = retrofit.create(APIList.class);
        }
        return instance;
    }

    public void setOnDatabaseListener(MyScheduleViewModel.MyScheduleListener databaseListener) {
        this.databaseListener = databaseListener;
    }
}
