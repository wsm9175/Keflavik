package com.example.keflavik.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.keflavik.R;
import com.example.keflavik.api.APIList;

import retrofit2.Retrofit;

abstract public class BaseActivity extends AppCompatActivity {

    Context mContext;

    Retrofit retrofit;
    APIList apiList;

    TextView titleTxt;
    TextView titleLeftTxt;
    ImageView backImg;
    FrameLayout actionBarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        /*retrofit = ServerAPI.getRetrofit(mContext);
        apiList = retrofit.create(APIList.class);*/

        if(getSupportActionBar() != null){
            setCustomActivityBar();
        }
    }

    void setCustomActivityBar(){
        ActionBar defaultActionBar = getSupportActionBar();
        defaultActionBar.setElevation(0);
        defaultActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        defaultActionBar.setCustomView(R.layout.custom_action_bar);

        Toolbar myToolbar = (Toolbar)defaultActionBar.getCustomView().getParent();
        //getParent 0, 0 만들기
        myToolbar.setContentInsetsAbsolute(0, 0);

        titleTxt = defaultActionBar.getCustomView().findViewById(R.id.titleTxt);
        titleLeftTxt = defaultActionBar.getCustomView().findViewById(R.id.titleLeftTxt);
        backImg = defaultActionBar.getCustomView().findViewById(R.id.backImg);
        actionBarLayout = defaultActionBar.getCustomView().findViewById(R.id.actionBarLayout);



        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
