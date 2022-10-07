package com.example.keflavik.viewmodel;

import android.content.Context;

import com.example.keflavik.databinding.ActivityWorkDetailBinding;
import com.example.keflavik.model.WorkDetailDatabase;

public class WorkDetailViewModel {
    //1번
    private WorkDetailDatabase database;
    private Context context;
    private ActivityWorkDetailBinding binding;
    //2번
    public WorkDetailViewModel(WorkDetailDatabase database, Context context, ActivityWorkDetailBinding binding) {
        this.database = database;
        this.context = context;
        this.binding = binding;
        //9번
        database.setOnDatabaseListener(getDatabaseListener());
    }

    //3번
    public interface WorkDetailListener{
        void onSuccess();
        void onFailed();
    }

    //7번
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
