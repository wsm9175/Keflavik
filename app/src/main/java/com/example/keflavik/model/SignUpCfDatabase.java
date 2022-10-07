package com.example.keflavik.model;

import android.content.Context;
import android.util.Log;

import com.example.keflavik.api.APIList;
import com.example.keflavik.api.ServerAPI;
import com.example.keflavik.datas.UserData;
import com.example.keflavik.viewmodel.SignUpCfViewModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpCfDatabase {
    static Retrofit retrofit;
    static APIList apiList;

    private static SignUpCfDatabase instance;
    private UserData user;
    private SignUpCfViewModel.SignUpListener databaseListener;


    public static SignUpCfDatabase getInstance(Context mContext){
        if(instance == null){
            instance = new SignUpCfDatabase();
            retrofit = ServerAPI.getRetrofit(mContext);
            apiList = retrofit.create(APIList.class);
        }
        return instance;
    }

    public void dupCheck(JsonObject object){

        apiList.getPhoneDupCheck(object).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    databaseListener.onSuccess(response.body().toString());


                }else {
                    Log.d("문제", "중복검사 실패");
                    databaseListener.onFailed(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }


    public void certification(String phoneNumber){
        apiList.certificationCheck(phoneNumber).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.d("문제 인증번호 확인", response.body());
                    databaseListener.onCertificationSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                databaseListener.onCertificationFailed();
            }
        });
    }


    public void setOnDatabaseListener(SignUpCfViewModel.SignUpListener databaseListener){
        this.databaseListener = databaseListener;
    }

    public interface DatabaseListener{
        void onChanged();
    }
}
