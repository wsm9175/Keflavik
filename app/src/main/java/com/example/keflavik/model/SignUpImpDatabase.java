package com.example.keflavik.model;

import android.content.Context;

import com.example.keflavik.api.APIList;
import com.example.keflavik.datas.UserData;
import com.example.keflavik.viewmodel.SignUpImpViewModel;

import retrofit2.Retrofit;

public class SignUpImpDatabase {
    static Retrofit retrofit;
    static APIList apiList;
    private SignUpImpViewModel.SignUpListner databaseListener;

    private static SignUpImpDatabase instance;
    private UserData user;

    public SignUpImpDatabase(){
        //?
    }

    public void signUp(){

    }

    //이건 메인 액티비티에서 사용할 것
    public static SignUpImpDatabase getInstance(Context context){
        if(instance == null){
            instance = new SignUpImpDatabase();
        }
        return instance;
    }

    //이건 뷰모델에서 쓰는 것
    public void setDatabaseListener(SignUpImpViewModel.SignUpListner databaseListener){
        this.databaseListener = databaseListener;
    }



}
