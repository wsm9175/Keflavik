package com.example.keflavik.model;

import android.content.Context;
import android.util.Log;

import com.example.keflavik.api.APIList;
import com.example.keflavik.api.ServerAPI;
import com.example.keflavik.datas.LoginData;
import com.example.keflavik.datas.UserData;
import com.example.keflavik.viewmodel.LoginViewModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginDatabase {
    static Retrofit retrofit;
    static APIList apiList;
    private LoginViewModel.GetUserListener databaseListener;


    private static LoginDatabase instance;
    private UserData user;
/*    private String id;
    private String pw;*/

    public LoginDatabase() {
        //여기는 나중에 기본값 가져올때
    }

    //이건 로그인 메인 model에서 생성하는 것
    public static LoginDatabase getInstance(Context mContext) {
        if (instance == null) {
            instance = new LoginDatabase();
            retrofit = ServerAPI.getRetrofit(mContext);
            apiList = retrofit.create(APIList.class);
        }
        return instance;
    }

    public void setDatabaseListener(LoginViewModel.GetUserListener databaseListener) {
        this.databaseListener = databaseListener;
    }

    public void login(JsonObject object) {
        apiList.putRequestSignUp(object).enqueue(new Callback<LoginData>() {
            //서버에서 아이디 비번을 맞게 보냈을때
            @Override
            public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                //response.body().getId();

                if (response.isSuccessful()) {
                    Log.d("문제", "로그인 성공" + response.body().getAccessToken());
                    databaseListener.onSuccess(response.body().getAccessToken());
                } else {
                    Log.d("문제", "로그인 실패11");
                    databaseListener.onFailed(response.errorBody().toString());
                }

            }

            //잘못된 값일때
            @Override
            public void onFailure(Call<LoginData> call, Throwable t) {
                Log.d("문제", t.getMessage().toString());
                databaseListener.onFailed(t.toString());
            }
        });
    }


}


