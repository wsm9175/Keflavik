package com.example.keflavik.model;

import android.content.Context;

import com.example.keflavik.api.APIList;
import com.example.keflavik.api.ServerAPI;
import com.example.keflavik.viewmodel.AddApartmentViewModel;

import retrofit2.Retrofit;

public class AddApartmentDatabase {
    static Retrofit retrofit;
    static APIList apiList;
    private AddApartmentViewModel.AddApartmentListener databaseListener;

    private static AddApartmentDatabase instance;

    public static AddApartmentDatabase getInstance(Context context){
        if(instance == null){
            instance = new AddApartmentDatabase();
            retrofit = ServerAPI.getRetrofit(context);
            apiList = retrofit.create(APIList.class);
        }
        return instance;
    }

    public void setDatabaseListener(AddApartmentViewModel.AddApartmentListener databaseListener) {
        this.databaseListener = databaseListener;
    }
}
