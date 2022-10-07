package com.example.keflavik.viewmodel;

import android.content.Context;

import com.example.keflavik.databinding.ActivitySignUpCfBinding;
import com.example.keflavik.databinding.FragmentWorkBinding;
import com.example.keflavik.model.SignUpCfDatabase;
import com.example.keflavik.model.WorkFragmentDatabase;
import com.example.keflavik.pragments.WorkFragment;

public class WorkFragmentViewModel {
    private WorkFragmentDatabase database;
    private Context mContext;
    private FragmentWorkBinding binding;

    public WorkFragmentViewModel(Context mContext, FragmentWorkBinding binding, WorkFragmentDatabase database) {
        this.database = database;
        this.mContext = mContext;
        this.binding = binding;
        database.setOnDatabaseListener(getDatabaseListener());
    }

    public void getCity(){
        //여기서 요청을 하고
        database.getCity(/*아마 토큰을 매개변수로 넣어서 내가 선택한 도시만? 가져올 것*/);
    }

    public WorkListener getDatabaseListener(){
        return new WorkListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed() {

            }
        };
    }

    public interface WorkListener{
        void onSuccess();
        void onFailed();
    }

}
