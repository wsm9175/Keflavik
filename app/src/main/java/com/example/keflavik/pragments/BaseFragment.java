package com.example.keflavik.pragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.keflavik.R;

import retrofit2.Retrofit;

abstract public class BaseFragment extends Fragment {
    Context mContext;

    TextView titleTxt;
    TextView titleLeftTxt;
    ImageView backImg;
    FrameLayout actionBarLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = requireContext();

    }

    void setCustomActivityBar(){
        //getSupportActionBar();
        //프레그먼트는 이렇게 사용?
        ActionBar defaultActionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
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
                //finish();
            }
        });
    }


}
