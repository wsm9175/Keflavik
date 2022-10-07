package com.example.keflavik.model;

import android.content.Context;

import com.example.keflavik.api.APIList;
import com.example.keflavik.api.ServerAPI;
import com.example.keflavik.viewmodel.WorkDetailViewModel;

import retrofit2.Retrofit;

public class WorkDetailDatabase {
    //4번
    static Retrofit retrofit;
    static APIList apiList;
    private WorkDetailViewModel.WorkDetailListener databaseListener;
    private static WorkDetailDatabase instance;

    //5번
    public static WorkDetailDatabase getInstance(Context mContext){
        if(instance == null){
            instance = new WorkDetailDatabase();
            retrofit = ServerAPI.getRetrofit(mContext);
            apiList = retrofit.create(APIList.class);
        }
        return instance;
    }
    //6번
    public void setOnDatabaseListener(WorkDetailViewModel.WorkDetailListener databaseListener){
        this.databaseListener = databaseListener;
    }

}
