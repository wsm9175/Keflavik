package com.example.keflavik.viewmodel;

import android.content.Context;

import com.example.keflavik.databinding.FragmentMyScheduleBinding;
import com.example.keflavik.model.MyScheduleDatabase;
import com.example.keflavik.model.WorkDetailDatabase;

public class MyScheduleViewModel {
    //1번
    private MyScheduleDatabase database;
    private Context context;
    private FragmentMyScheduleBinding binding;

    public MyScheduleViewModel(MyScheduleDatabase database, Context context, FragmentMyScheduleBinding binding) {
        this.database = database;
        this.context = context;
        this.binding = binding;
        database.setOnDatabaseListener(getDatabaseListener());
    }

    public interface MyScheduleListener{
        void onSuccess();
        void onFailed();
    }

    public MyScheduleListener getDatabaseListener(){
        return new MyScheduleListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed() {

            }
        };
    }

}
