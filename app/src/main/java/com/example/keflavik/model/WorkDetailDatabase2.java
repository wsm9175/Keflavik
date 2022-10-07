package com.example.keflavik.model;

import android.content.Context;

import com.example.keflavik.api.APIList;
import com.example.keflavik.api.ServerAPI;
import com.example.keflavik.viewmodel.WorkDetailViewModel2;

import retrofit2.Retrofit;

public class WorkDetailDatabase2 {
    static Retrofit retrofit;
    static APIList apiList;

    private WorkDetailViewModel2.WorkDetailListener databaseListener;
    private static WorkDetailDatabase2 instance;

    public static WorkDetailDatabase2 getInstance(Context mContext){
        if(instance == null){
            instance = new WorkDetailDatabase2();
            retrofit = ServerAPI.getRetrofit(mContext);
            apiList = retrofit.create(APIList.class);
        }
        return instance;
    }

    public void setOnDatabaseListener(WorkDetailViewModel2.WorkDetailListener databaseListener){
        this.databaseListener = databaseListener;
    }


}
