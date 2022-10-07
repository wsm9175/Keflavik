package com.example.keflavik.model;

import android.content.Context;
import android.util.Log;

import com.example.keflavik.TechDTO;
import com.example.keflavik.api.APIList;
import com.example.keflavik.api.ServerAPI;
import com.example.keflavik.datas.UserData;
import com.example.keflavik.viewmodel.SignUpImpViewModel2;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignUpImpDatabase2 {
    static Retrofit retrofit;
    static APIList apiList;
    private SignUpImpViewModel2.GetUserListener databaseListener;

    private static SignUpImpDatabase2 instance;
    private UserData user;

    public SignUpImpDatabase2(){
        //여기는 나중에 기본값 가져올때
    }

    public static SignUpImpDatabase2 getInstance(Context mContext){
        if(instance == null){
            instance = new SignUpImpDatabase2();
            retrofit = ServerAPI.getRetrofit(mContext);
            apiList = retrofit.create(APIList.class);
        }
        return instance;
    }

    public void setDatabaseListener(SignUpImpViewModel2.GetUserListener databaseListener){
        this.databaseListener = databaseListener;
    }

    public void businessSignUp(JsonObject object, JsonArray jsonArray, File file){
        //String v = "[{\"name\" : \"방충망\"},{\"name\" : \"페인트\"}]";
        apiList.businessSignUp(file, object, jsonArray.toString()).enqueue(new Callback<UserData>() {
        //apiList.businessSignUp(file, object, jsonArray).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {

                Log.d("뜨나??", response.body()+"");
                databaseListener.onSuccess();
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

                databaseListener.onFailed();
            }
        });
    }

    public void individualSignUp(UserData user, RequestBody requestBody){
        apiList.individualSignUp(user, requestBody).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                databaseListener.onSuccess();
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.d("페일",t.toString());

            }
        });
    }


}
