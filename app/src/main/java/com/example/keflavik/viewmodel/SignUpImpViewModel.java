package com.example.keflavik.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.example.keflavik.databinding.ActivitySingUpImpBinding;
import com.example.keflavik.model.SignUpImpDatabase;
import com.example.keflavik.datas.UserData;
import com.example.keflavik.datas.GlobalData;

public class SignUpImpViewModel extends BaseObservable {
    private SignUpImpDatabase database;
    private Context mContext;
    private ActivitySingUpImpBinding binding;
    private UserData user;

    public SignUpImpViewModel(Context mContext, ActivitySingUpImpBinding binding, SignUpImpDatabase database){
        this.database = database;
        this.mContext = mContext;
        this.binding = binding;
        database.setDatabaseListener(getDatabaseListener());
    }

    public void signUp(){
        GlobalData gUser = new GlobalData();
        user = gUser.getGlobalUser();

        //유저의 정보 넘기기
        user.setPw(binding.pwEdt.getText().toString());
        user.setEmail(binding.emailIdEdt.getText().toString()+"@"+binding.emailSpinner.getSelectedItem().toString());


        //두개의 비밀번호가 같은지 확인하는 것
        if(binding.pwEdt.getText().toString().equals(binding.pwCheckEdt.getText().toString())){
            Toast.makeText(mContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        user.setPw(binding.pwEdt.getText().toString());
        user.setEmail(binding.emailIdEdt.getText().toString()+"@"+binding.emailSpinner.getSelectedItem());
        Log.d("문제 이메일", binding.emailIdEdt.getText().toString()+"@"+binding.emailSpinner.getSelectedItem());

        database.signUp();
    }

    public SignUpListner getDatabaseListener(){
        return new SignUpListner() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed(String t) {

            }
        };
    }

    public interface SignUpListner{
        void onSuccess();
        void onFailed(String t);
    }




    //@@@@@@@@@@@@@@@@@@@@@@@@@@








}



//@@@@@@@@@@@@@@@@@@@


