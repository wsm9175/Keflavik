package com.example.keflavik.model;

import android.content.Context;

import com.example.keflavik.api.APIList;
import com.example.keflavik.api.ServerAPI;
import com.example.keflavik.viewmodel.WorkFragmentViewModel;

import retrofit2.Retrofit;

public class WorkFragmentDatabase {

    static Retrofit retrofit;
    static APIList apiList;
    private WorkFragmentViewModel.WorkListener databaseListener;

    private static WorkFragmentDatabase instance;

    public static WorkFragmentDatabase getInstance(Context mContext){
        if(instance == null){
            instance = new WorkFragmentDatabase();
            retrofit = ServerAPI.getRetrofit(mContext);
            apiList = retrofit.create(APIList.class);
        }
        return instance;
    }

    public void setOnDatabaseListener(WorkFragmentViewModel.WorkListener databaseListener){
        this.databaseListener = databaseListener;
    }

    public void getCity(){
        //여기서 apiList 해서 서버정보를 가져오고
        //ViewModel 에서 onSuccess 를 통해 정보를 넣어준다
    }






}
