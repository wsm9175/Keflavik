package com.example.keflavik.viewmodel;

import android.content.Context;

import com.example.keflavik.databinding.ActivityWorkDetail2Binding;
import com.example.keflavik.databinding.ActivityWorkDetailBinding;
import com.example.keflavik.model.WorkDetailDatabase2;

public class WorkDetailViewModel2 {
    //1번
    private WorkDetailDatabase2 database;
    private Context context;
    private ActivityWorkDetail2Binding binding;

    public WorkDetailViewModel2(WorkDetailDatabase2 database, Context context, ActivityWorkDetail2Binding binding){
        this.database = database;
        this.context = context;
        this.binding = binding;
        database.setOnDatabaseListener(getDatabaseListener());
    }

    public interface WorkDetailListener{
        void onSuccess();
        void onFailed();
    }

    public WorkDetailListener getDatabaseListener(){
        return new WorkDetailListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed() {

            }
        };
    }

}
