package com.example.keflavik.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.example.keflavik.databinding.ActivityLoginBinding;
import com.example.keflavik.model.LoginDatabase;
import com.example.keflavik.datas.UserData;
import com.example.keflavik.utils.ContextUtil;
import com.example.keflavik.view.MainActivity;
import com.google.gson.JsonObject;

public class LoginViewModel extends BaseObservable {
    private LoginDatabase database;
    private Context mContext;
    private ActivityLoginBinding binding;
    private UserData user;

    public LoginViewModel(Context mContext, ActivityLoginBinding binding, LoginDatabase database){
        this.database = database;
        this.mContext = mContext;
        this.binding = binding;
        database.setDatabaseListener(getDatabaseListener());
    }


    public GetUserListener getDatabaseListener(){
        return new GetUserListener() {
            @Override
            //public void onSuccess(User user2) {
            public void onSuccess(String body) {
                //user = user2;
                Toast.makeText(mContext, binding.loginIdEdt.getText()+"님 환영합니다.", Toast.LENGTH_SHORT).show();

                ContextUtil token = new ContextUtil();
                token.setLoginToken(mContext, body);

                Log.d("토큰 확인?", body);

                ///////////////
                Intent myIntent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(myIntent);
                //////////////
            }

            @Override
            public void onFailed(String t) {
                Toast.makeText(mContext, "로그인 실패?", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public interface GetUserListener {
        void onSuccess(String body);
        void onFailed(String t);
    }



    public void login(){
        //잘못 입력했을때 ex) 빈칸
        if(binding.loginIdEdt.getText().length() < 5 && binding.loginPwEdt.getText().length() < 5){
            //Toast.makeText(mContext, binding.loginIdEdt.getText().length()+"", Toast.LENGTH_SHORT).show();
            Toast.makeText(mContext, "아이디와 비밀호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            //임시 바로 로그인
/*            Intent myIntent = new Intent(mContext, MainActivity.class);
            mContext.startActivity(myIntent);*/
        }else{
            //우선 아이디 비번은 입력했을때
            JsonObject object = new JsonObject();
            object.addProperty("phoneNumber", binding.loginIdEdt.getText().toString());
            object.addProperty("pw", binding.loginPwEdt.getText().toString());
            //database.login(binding.loginIdEdt.getText().toString(), binding.loginPwEdt.getText().toString());
            database.login(object);

        }

    }



}
